package com.momentum.installer;

import javax.swing.*;

/**
 * @author linus
 * @since 02/22/2023
 */
public class InstallerFrame extends JFrame {

    /**
     * The main com.momentum.installer frame
     */
    public InstallerFrame() {

        // setup frame
        setTitle("Momentum Installer");
        setSize(500, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // update defaults
        // UIDefaults defaults = UIManager.getDefaults();
        // defaults.put("activeCaption", new ColorUIResource(Color.darkGray));
        // defaults.put("activeCaptionText", new ColorUIResource(Color.white));
        // JFrame.setDefaultLookAndFeelDecorated(true);

        // display the login gui
        LoginGui loginGui = new LoginGui();
        add(loginGui);
        loginGui.setVisible(true);

        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
    }
}