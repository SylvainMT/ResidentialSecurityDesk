/*---------------------------------------------------------------------------------------------------------------------
 -     Copyright (C) 2022  Sylvain Tousignant                                                                         -
 -                                                                                                                    -
 -     This program is free software: you can redistribute it and/or modify                                           -
 -     it under the terms of the GNU Affero General Public License as                                                 -
 -     published by the Free Software Foundation, either version 3 of the                                             -
 -     License, or (at your option) any later version.                                                                -
 -                                                                                                                    -
 -     This program is distributed in the hope that it will be useful,                                                -
 -     but WITHOUT ANY WARRANTY; without even the implied warranty of                                                 -
 -     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                                                  -
 -     GNU Affero General Public License for more details.                                                            -
 -                                                                                                                    -
 -     You should have received a copy of the GNU Affero General Public License                                       -
 -     along with this program.  If not, see <https://www.gnu.org/licenses/agpl-3.0.html>                             -
 ---------------------------------------------------------------------------------------------------------------------*/

package ca.sylvaint.rds.cryptography;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

public final class AES {

    private static final int ITERATION_COUNT = 65536; // Higher Iterations equals greater security.
    private static final int KEY_LENGTH = 256; // One of 128|192|256
    private static final int INIT_VECTOR_SIZE = 16; // HAS to be 16
    private static final int SALT_SIZE = 32; // Can be of any length
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    /**
     * Encrypts a string using AES encryption.
     * @param plainText The String to Encrypt
     * @param passKey The key used to encrypt the string.
     * @return The Hex encrypted string
     * @throws AESExceptions Will throw this exception on any error encountered.
     */
    public static String encrypt(String plainText, String passKey) throws AESExceptions {
        IvParameterSpec iv  = generateIv();
        byte[] salt = getNextSalt();
        SecretKey secretKey = getSecretKey(passKey, salt);
        byte[] encrypted = encryptPasswordBased(plainText, secretKey, iv);
        return getHexFormattedDelimitedString(iv, salt, encrypted);
    }

    /**
     * Takes a Hex encrypted string and a passkey and returns the decrypted string.
     * @param cypher The Hex encrypted string.
     * @param passKey The Passkey
     * @return The decrypted string.
     * @throws AESExceptions Will throw this exception on any error encountered.
     */
    public static String decrypt(String cypher, String passKey) throws AESExceptions {
        CypherParts cypherParts = new CypherParts(cypher);
        SecretKey secretKey = getSecretKey(passKey, cypherParts);
        return getDecrypted(cypherParts, secretKey);
    }

    private static String getHexFormattedDelimitedString(IvParameterSpec iv, byte[] salt, byte[] encrypted) {
        return HexFormat.of().formatHex(encrypted) +
                ':' +
                HexFormat.of().formatHex(salt) +
                ':' +
                HexFormat.of().formatHex(iv.getIV());
    }



    private static String getDecrypted(CypherParts cypherParts, SecretKey secretKey) {
        try {
            return decryptPasswordBased(cypherParts.getEncrypted(), secretKey, cypherParts.getIv());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new AESExceptions("Provided cypher is not formatted properly", e);
        }
    }

    private static SecretKey getSecretKey(String passKey, CypherParts cypherParts) {
       return getSecretKey(passKey, cypherParts.getSalt());
    }

    private static SecretKey getSecretKey(String passKey, byte[] salt) {
        SecretKey secretKey;
        try {
            secretKey = getKeyFromPassword(passKey, salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AESExceptions("Provided cypher is not formatted properly", e);
        }
        return secretKey;
    }

    private static SecretKey getKeyFromPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
    }

    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[INIT_VECTOR_SIZE];
        getSecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static SecureRandom getSecureRandom() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return secureRandom;
    }

    private static byte[] getNextSalt() {
        byte[] salt = new byte[SALT_SIZE];
        getSecureRandom().nextBytes(salt);
        return salt;
    }

    private static byte[] encryptPasswordBased(String plainText, SecretKey key, IvParameterSpec iv) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new AESExceptions(e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new AESExceptions(e);
        }
        try {
            return cipher.doFinal(plainText.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new AESExceptions(e);
        }
    }

    private static String decryptPasswordBased(byte[] cipherText, SecretKey key, IvParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return new String(cipher.doFinal(cipherText));
    }

    private static class CypherParts {
        private final byte[] salt;
        private final IvParameterSpec iv;
        private final byte[] encrypted;

        public CypherParts(String cypher) {

            String[] cypherParts = cypher.split(":");
            if (cypherParts.length != 3) {
                throw new AESExceptions("Provided cypher is not formatted properly");
            }

            for (String hexString : cypherParts) {
                if (hexString.length() % 2 != 0) {
                    throw new AESExceptions("Provided cypher is not formatted properly");
                }
            }

            String encryptedHex = cypherParts[0];
            String saltHex = cypherParts[1];
            String ivHex = cypherParts[2];

            byte[] ivBytes = HexFormat.of().parseHex(ivHex);

            this.salt = HexFormat.of().parseHex(saltHex);
            this.iv = new IvParameterSpec(ivBytes);
            this.encrypted = HexFormat.of().parseHex(encryptedHex);
        }

        public byte[] getSalt() {
            return salt;
        }

        public IvParameterSpec getIv() {
            return iv;
        }

        public byte[] getEncrypted() {
            return encrypted;
        }
    }

    public static class AESExceptions extends RuntimeException {
        public AESExceptions(String message) {
            super(message);
        }

        public AESExceptions(String message, Throwable cause) {
            super(message, cause);
        }

        public AESExceptions(Throwable cause) {
            super(cause);
        }
    }


}
