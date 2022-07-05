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

public final class Configurations {

    /**
     * This class cannot be instantiated
     */
    private Configurations(){}


    public static ConnSettings loadConfigurations() {
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

        return null;
    }


}
