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

package ca.sylvaint.rds.guistart;

import ca.sylvaint.rds.auth.LoginForm;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.util.Enumeration;

public class TempStart {
    public static void main(String[] args) {

        FlatLightFlatIJTheme.setup();

        try {
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        }  catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        setUIFontSize(125);

        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
        });
    }

    private static void setUIFontSize(int percentage) {
        float multiplyValue = percentage / 100f;
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource fontUIResource) {
                int currentSize = fontUIResource.getSize();
                float newSize = (float) Math.round(currentSize * multiplyValue);
                UIManager.put(key, fontUIResource.deriveFont(newSize));
            }
        }
    }
}
