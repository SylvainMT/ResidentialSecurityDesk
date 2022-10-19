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

package ca.sylvaint.rds.auth.loginform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public final class View {
    

    private View() {}

    public static void initView(Components components) {
        JFrame frame = components.getFrame();
        frame.setSize(640,480);
        // Make frame appear center of the screen.
        frame.setLocationRelativeTo(null);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        
        addUserNameField(contentPanel, components.getUserNameLabel(), components.getUserNameField());
        addPasswordField(contentPanel, components.getPasswordLabel(), components.getPasswordField());
        addButtons(contentPanel, components.getForgotPasswordButton(), components.getCancelButton(), components.getLoginButton());

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(Box.createVerticalStrut(50), BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(50), BorderLayout.SOUTH);
        frame.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
        frame.add(Box.createHorizontalStrut(50), BorderLayout.WEST);

        contentPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JPanel contentPanel = (JPanel) e.getComponent();

                Dimension dimension = contentPanel.getMinimumSize();
                dimension.setSize(dimension.getWidth() + 150, dimension.getHeight() + 150);
                frame.setMinimumSize(dimension);
            }
        });
    }

    

    private static void addUserNameField(JPanel contentPanel, JLabel label, JTextField textField) {
        GridBagConstraints labelGBC = new GridBagConstraints();
        labelGBC.gridx = 0;
        labelGBC.gridy = 0;
        labelGBC.gridheight = 1;
        labelGBC.gridwidth = 1;
        labelGBC.anchor = GridBagConstraints.EAST;
        contentPanel.add(label, labelGBC);
        
        GridBagConstraints textFieldGBC = new GridBagConstraints();
        textFieldGBC.gridx = 1;
        textFieldGBC.gridy = 0;
        textFieldGBC.gridheight = 1;
        textFieldGBC.gridwidth = 2;
        textFieldGBC.fill = GridBagConstraints.HORIZONTAL;
        textFieldGBC.anchor = GridBagConstraints.WEST;
        contentPanel.add(textField, textFieldGBC);
    }

    private static void addPasswordField(JPanel contentPanel, JLabel label, JPasswordField passwordField) {
        GridBagConstraints labelGBC = new GridBagConstraints();
        labelGBC.gridx = 0;
        labelGBC.gridy = 1;
        labelGBC.gridheight = 1;
        labelGBC.gridwidth = 1;
        labelGBC.anchor = GridBagConstraints.EAST;
        contentPanel.add(label, labelGBC);

        GridBagConstraints passwordFieldGBC = new GridBagConstraints();
        passwordFieldGBC.gridx = 1;
        passwordFieldGBC.gridy = 1;
        passwordFieldGBC.gridheight = 1;
        passwordFieldGBC.gridwidth = 2;
        passwordFieldGBC.fill = GridBagConstraints.HORIZONTAL;
        passwordFieldGBC.anchor = GridBagConstraints.WEST;
        contentPanel.add(passwordField, passwordFieldGBC);
    }

    private static void addButtons(JPanel contentPanel, JButton forgotPasswordButton, JButton cancelButton, JButton loginButton) {
        Insets buttonInsets = new Insets(5, 5, 5, 5);
        
        GridBagConstraints forgotPasswordButtonGBC = new GridBagConstraints();
        forgotPasswordButtonGBC.gridx = 0;
        forgotPasswordButtonGBC.gridy = 2;
        forgotPasswordButtonGBC.gridheight = 1;
        forgotPasswordButtonGBC.gridwidth = 1;
        forgotPasswordButtonGBC.anchor = GridBagConstraints.CENTER;
        forgotPasswordButtonGBC.insets = buttonInsets;
        contentPanel.add(forgotPasswordButton, forgotPasswordButtonGBC);

        GridBagConstraints cancelButtonGBC = new GridBagConstraints();
        cancelButtonGBC.gridx = 1;
        cancelButtonGBC.gridy = 2;
        cancelButtonGBC.gridheight = 1;
        cancelButtonGBC.gridwidth = 1;
        cancelButtonGBC.anchor = GridBagConstraints.CENTER;
        cancelButtonGBC.insets = buttonInsets;
        contentPanel.add(cancelButton, cancelButtonGBC);

        GridBagConstraints loginButtonGBC = new GridBagConstraints();
        loginButtonGBC.gridx = 2;
        loginButtonGBC.gridy = 2;
        loginButtonGBC.gridheight = 1;
        loginButtonGBC.gridwidth = 1;
        loginButtonGBC.anchor = GridBagConstraints.CENTER;
        loginButtonGBC.insets = buttonInsets;
        contentPanel.add(loginButton, loginButtonGBC);
    }
}
