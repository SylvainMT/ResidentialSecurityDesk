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

package ca.sylvaint.rds.dbConn;

public final class Connection {
    /*
    Singleton Pattern

    Builds a MariaDB JDBC connection. Stores it in memory and gives the object on demand to any class within the
    Local Persistence module.

    Could be expanded to work with connection pools if needed.

    Must also contain a static function to test DB Connection using a ConnSettings Record
    */
}
