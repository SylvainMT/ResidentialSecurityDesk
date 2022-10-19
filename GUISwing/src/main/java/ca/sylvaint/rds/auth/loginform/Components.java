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
import java.awt.event.KeyEvent;

public class Components {

    private JFrame frame;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton ForgotPasswordButton;

    public Components() {
        buildFrame();
        buildUserNameField();
        buildPasswordField();
        buildButtons();
    }

    private void buildFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Please Login");
        //frame.setResizable(false);
    }

    private void buildUserNameField() {
        usernameField = new JTextField();


        usernameLabel = new JLabel("Username: ");
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        usernameLabel.setLabelFor(usernameField);

    }

    private void buildPasswordField() {
        passwordField = new JPasswordField();


        passwordLabel = new JLabel("Password: ");
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setLabelFor(passwordField);

    }

    private void buildButtons() {
        loginButton = new JButton("Login");
        loginButton.setMnemonic(KeyEvent.VK_L);

        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic(KeyEvent.VK_C);

        ForgotPasswordButton = new JButton("Forgot Password");
        ForgotPasswordButton.setMnemonic(KeyEvent.VK_F);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getUserNameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JTextField getUserNameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getForgotPasswordButton() {
        return ForgotPasswordButton;
    }
}
