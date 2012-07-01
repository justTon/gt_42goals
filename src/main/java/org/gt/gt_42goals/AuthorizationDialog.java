package org.gt.gt_42goals;

import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIDOMNamedNodeMap;
import org.mozilla.interfaces.nsIDOMNode;
import org.mozilla.interfaces.nsIDOMNodeList;
import ru.atomation.jbrowser.impl.JBrowserBuilder;
import ru.atomation.jbrowser.impl.JBrowserCanvas;
import ru.atomation.jbrowser.impl.JBrowserComponent;
import ru.atomation.jbrowser.impl.JComponentFactory;
import ru.atomation.jbrowser.interfaces.BrowserAdapter;
import ru.atomation.jbrowser.interfaces.BrowserManager;

import javax.swing.*;
import java.awt.*;

/**
 * User: AKazakov
 * Date: 01.07.12 13:43
 */
public class AuthorizationDialog extends JDialog {

    private JBrowserComponent<?> browser = null;
    private String approvedToken = null;

    public AuthorizationDialog() {
        this(null);
    }

    public AuthorizationDialog(String url) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Authorization in 42goals required");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize((int) (screenSize.getWidth() * 0.75f),
                (int) (screenSize.getHeight() * 0.75f));
        setLocationRelativeTo(null);
        setModal(true);

        browser = createBrowser();
        add(browser.getComponent(), BorderLayout.CENTER);
        setUrl(url);
    }

    private JBrowserComponent<?> createBrowser() {
        BrowserManager browserManager = new JBrowserBuilder().buildBrowserManager();
        JComponentFactory<Canvas> canvasFactory = browserManager.getComponentFactory(JBrowserCanvas.class);

        final JBrowserComponent<?> browser = canvasFactory.createBrowser();
        browser.addBrowserListener(new BrowserAdapter() {

            @Override
            public void onLoadingEnded() {
                nsIDOMDocument document = browser.getWebBrowser()
                        .getContentDOMWindow().getDocument();
                nsIDOMNodeList inputs = document.getElementsByTagName("input");

                for (long i = 0; i < inputs.getLength(); i++) {
                    nsIDOMNode input = inputs.item(i);
                    nsIDOMNamedNodeMap attrs = input.getAttributes();

                    if (attrs.getLength() > 0) {
                        if (attrs.getNamedItem("id") == null && attrs.getNamedItem("name") == null) {
                            nsIDOMNode value = attrs.getNamedItem("value");

                            if (value != null)
                                approvedToken = value.getNodeValue();
                            System.out.println("Token approved: " + approvedToken);
                            setVisible(false);
                        }
                    }
                }
            }
        });

        return browser;
    }

    public void setUrl(String url) {
        if (url != null)
            browser.setUrl(url);
    }

    public String getApprovedToken() {
        return approvedToken;
    }
}
