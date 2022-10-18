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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class Configurations {
    private static final String DEFAULT_DB_CONFIG_FILE = "db-config.xml";
    private static final String DEFAULT_RELATIVE_PATH_TO_PROPERTIES_FILES = "properties";

    private final String dbConfigFileName;
    private final String relativePathToPropertiesFiles;

    private DbConfig dbConfig = null;

    /**
     * This class cannot be instantiated
     */
    public Configurations(){
        this(DEFAULT_RELATIVE_PATH_TO_PROPERTIES_FILES, DEFAULT_DB_CONFIG_FILE);
    }

    public Configurations(String relativePathToPropertiesFiles) {
        this(relativePathToPropertiesFiles, DEFAULT_DB_CONFIG_FILE);
    }

    public Configurations(String relativePathToPropertiesFiles, String dbConfigFileName) {
        this.dbConfigFileName = dbConfigFileName;
        this.relativePathToPropertiesFiles = relativePathToPropertiesFiles;
    }

    /*
        - Confirm existence of the configuration file
            -> if exists : Load configuration file
            -> decrypt values
            -> populate a record object with the config file values.
            -> return the record object.

            -> if not found : Query user, via GUI Module for configuration details.
            -> clean entered values (no SQL present)
            -> Create a connSettings record object with values passed.
            -> test DB Connection with connSettings
            -> if test fails, re-query for configurations
            -> if test passes, encrypt values,
            -> save configuration file
            -> return connSettings
         */
    public DbConfig getDbConfig () {
        if (this.dbConfig == null) {
            this.dbConfig = loadDbConfig();
        }
        return this.dbConfig;
    }

    private DbConfig loadDbConfig()  {
        if (!doesPropertiesDirectoryExist()) {
            throw new DirectoryNotFoundException("The Directory \"" + this.relativePathToPropertiesFiles + "\" was not found.");
        }
        Path dbConfigFile = Paths.get(relativePathToPropertiesFiles, dbConfigFileName);
        if (!doesDbConfigFileExist(dbConfigFile) || !isDbConfigFileReadable(dbConfigFile)) {
            System.err.println("File does not exist or is not readable");
            throw new ConfigFileNotFoundException("The Config file \"" + this.relativePathToPropertiesFiles + "\\" +
                    this.dbConfigFileName + "\" was not found." );
        }

        try (InputStream inputStream = Files.newInputStream(dbConfigFile)) {
            Properties dbConfigProp = new Properties();
            dbConfigProp.loadFromXML(inputStream);
            DbConfig databaseConfig = new DbConfig(
                    dbConfigProp.getProperty("JDBC_DRIVER"),
                    dbConfigProp.getProperty("DB_URL_PROTOCOL"),
                    dbConfigProp.getProperty("DB_URL"),
                    dbConfigProp.getProperty("DB_PORT"),
                    dbConfigProp.getProperty("DB_USER"),
                    dbConfigProp.getProperty("DB_USER_PASS"),
                    dbConfigProp.getProperty("DB_SCHEMA"),
                    dbConfigProp.getProperty("KEYSTORE"),
                    dbConfigProp.getProperty("KEYSTORE_PASSWORD")
            );
            return databaseConfig;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean doesDbConfigFileExist(Path dbConfigFile) {
        boolean dbConfigFileExists;
        try {
            dbConfigFileExists = Files.exists(dbConfigFile);
        } catch (InvalidPathException e) {
            dbConfigFileExists = false;
        }
        return dbConfigFileExists;
    }

    private boolean isDbConfigFileReadable(Path dbConfigFile) {
        boolean isDbConfigFileReadable;
        try {
            isDbConfigFileReadable = Files.isReadable(dbConfigFile);
        } catch (InvalidPathException e) {
            isDbConfigFileReadable = false;
        }
        return isDbConfigFileReadable;
    }

    private boolean doesPropertiesDirectoryExist() {
        boolean propertiesDirectoryExists;

        try {
            Path propertiesDirectory = Paths.get(relativePathToPropertiesFiles);
            propertiesDirectoryExists = Files.exists(propertiesDirectory);

        } catch (InvalidPathException e) {
            propertiesDirectoryExists = false;
        }
        return propertiesDirectoryExists;
    }

    class DirectoryNotFoundException extends RuntimeException {
        public DirectoryNotFoundException(String message) {
            super(message);
        }
    }

    class ConfigFileNotFoundException extends RuntimeException {
        public ConfigFileNotFoundException(String message) {
            super(message);
        }
    }


}
