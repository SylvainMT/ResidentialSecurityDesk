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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AESEncryptionTests {

    @Test
    void encryptAndThenDecryptAStringCorrectly() {
        //Arrange
        String expected = "SuperSecretNeedToKeepSecure**&&123";
        String secretKey = "extraSuperSecret";
        //Act
        String encrypted = AES.encrypt(expected, secretKey);
        String actual = AES.decrypt(encrypted,secretKey);
        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void throwExceptionWhenDecryptGivenNonHexString() {
        //Arrange
        String NonHexString = "nuDr0#lnu3-q8tHiSWa$t4F-U@adA";
        String secretKey = "Password123";
        //Act

        //Assert
        Assertions.assertThrows(AES.AESExceptions.class, () -> AES.decrypt(NonHexString, secretKey));

    }

    @Test
    void throwExceptionWhenDecryptGivenNonColonDelimitedHexString() {
        //Arrange
        String nonColonDelimitedHexString = "da2e769700bce161d6d71c7e3a1a423166f0d81dda00439031d5bef5a590962439835ab2a438a15fad98fe3071edba545d6979359b3836d07530306deb4731cc=f80d1afd37fd44b01342bad824313d1680c093670bfff8717d3ef8f6ce63e134=9d9591e66704ee6304a04384cc3d6f7a";
        String secretKey = "Password123";
        //Act

        //Assert
        Assertions.assertThrows(AES.AESExceptions.class, () -> AES.decrypt(nonColonDelimitedHexString, secretKey));

    }

    @Test
    void throwExceptionWhenDecryptGivenColonDelimitedHexStringWithTooManyParts() {
        //Arrange
        String nonColonDelimitedHexString = "da2e769700bce161:d6d71c7e3a1a423166f0d81dda00439031d5bef5a590962439835ab2a438a15fad98fe3071edba545d6979359b3836d07530306deb4731cc:f80d1afd37fd44b01342bad824313d1680c093670bfff8717d3ef8f6ce63e134:9d9591e66704ee6304a04384cc3d6f7a";
        String secretKey = "Password123";
        //Act

        //Assert
        Assertions.assertThrows(AES.AESExceptions.class, () -> AES.decrypt(nonColonDelimitedHexString, secretKey));

    }

    @Test
    void throwExceptionWhenDecryptGivenColonDelimitedHexStringWithTooFewParts() {
        //Arrange
        String nonColonDelimitedHexString = "da2e769700bce161d6d71c7e3a1a423166f0d81dda00439031d5bef5a590962439835ab2a438a15fad98fe3071edba545d6979359b3836d07530306deb4731cc:f80d1afd37fd44b01342bad824313d1680c093670bfff8717d3ef8f6ce63e1349d9591e66704ee6304a04384cc3d6f7a";
        String secretKey = "Password123";
        //Act

        //Assert
        Assertions.assertThrows(AES.AESExceptions.class, () -> AES.decrypt(nonColonDelimitedHexString, secretKey));

    }

    @Test
    void throwExceptionWhenDecryptGivenColonDelimitedNonHexString() {
        //Arrange
        String nonColonDelimitedHexString = "48fbbb35bffcd2a1d7ac7358bdb081ca647038b3edaf3b7a7ba703473787d99f5c88fb853f75c04ad9c275a36056278184a7a0efb3724ad9c79061afe481adb:ed657e8dd43de1f9a260dfcd7754b1a5f532fdf87086e523212a5c9ddfd1a870:e555530d72fba1327f9f0fa67a2c7968";
        String secretKey = "Password123";
        //Act

        //Assert
        Assertions.assertThrows(AES.AESExceptions.class, () -> AES.decrypt(nonColonDelimitedHexString, secretKey));

    }
}