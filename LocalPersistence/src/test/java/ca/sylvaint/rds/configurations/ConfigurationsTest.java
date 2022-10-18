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

package ca.sylvaint.rds.configurations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class ConfigurationsTest {

    @Test
    void getDbConfigTest () {
        //Arrange
        Configurations config = new Configurations();
        DbConfig dbConfig = config.getDbConfig();

        System.out.println(dbConfig.JdbcDriver());
        System.out.println(dbConfig.DbUrlProtocol());
        System.out.println(dbConfig.DbUrl());
        System.out.println(dbConfig.DbPort());
        System.out.println(dbConfig.DbSchema());
        System.out.println(dbConfig.DbUser());
        System.out.println(dbConfig.DbUserPass());
        System.out.println(dbConfig.Keystore());
        System.out.println(dbConfig.KeystorePassword());

    }

    @Test
    void expectExceptionWhenProvidedNonexistentDirectory() {
        //Arrange
        Path nonExistentPath = Paths.get("tempDoesNotExist");
        String expectedExceptionMessage = "The Directory \"tempDoesNotExist\" was not found.";
        if (Files.exists(nonExistentPath)) {
            try {
                deleteDirectoryRecursively(nonExistentPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Act
        Configurations config = new Configurations(nonExistentPath.toString());

        //Assert
        Exception exception = Assertions.assertThrows(Configurations.DirectoryNotFoundException.class, () -> config.getDbConfig());
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }


    // Utility Methods below...


    void deleteDirectoryRecursively (Path pathToDelete) throws IOException {
        Files.walkFileTree(pathToDelete, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}