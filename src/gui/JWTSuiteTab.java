package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;

import model.JWTSuiteTabModel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import app.helpers.JLabelLink;
import app.helpers.Strings;

public class JWTSuiteTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea jwtInputField;
	private RSyntaxTextArea jwtOuputField;
	private JButton jwtSignatureButton;
	private JTextArea jwtKeyArea;
	private JLabel lblEnterSecret;
	private JWTSuiteTabModel jwtSTM;
	private JButton creditButton;
	private JLabel lbRegisteredClaims;
	private JLabel lblExtendedVerificationInfo;

	public JWTSuiteTab(JWTSuiteTabModel jwtSTM) {
		drawGui();
		this.jwtSTM = jwtSTM;
	}

	public void updateSetView() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (!jwtInputField.getText().equals(jwtSTM.getJwtInput())) {
					jwtInputField.setText(jwtSTM.getJwtInput());
				}
				if (!jwtSignatureButton.getText().equals(jwtSTM.getVerificationLabel())) {
					jwtSignatureButton.setText(jwtSTM.getVerificationLabel());
				}
				if (!jwtOuputField.getText().equals(jwtSTM.getJwtJSON())) {
					jwtOuputField.setText(jwtSTM.getJwtJSON());
				}
				if (!jwtKeyArea.getText().equals(jwtSTM.getJwtKey())) {
					jwtKeyArea.setText(jwtSTM.getJwtKey());
				}
				if (!jwtSignatureButton.getBackground().equals(jwtSTM.getJwtSignatureColor())) {
					jwtSignatureButton.setBackground(jwtSTM.getJwtSignatureColor());
				}
				if (jwtKeyArea.getText().equals("")) {
					jwtSTM.setJwtSignatureColor(new JButton().getBackground());
					jwtSignatureButton.setBackground(jwtSTM.getJwtSignatureColor());
				}
				lblExtendedVerificationInfo.setText(jwtSTM.getVerificationResult());
				lbRegisteredClaims.setText(jwtSTM.getTimeClaimsAsText());
			}
		});
	}

	public void registerDocumentListener(DocumentListener jwtInputListener, DocumentListener jwtKeyListener) {
		jwtInputField.getDocument().addDocumentListener(jwtInputListener);
		jwtKeyArea.getDocument().addDocumentListener(jwtKeyListener);
	}

	private void drawGui() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblPasteJwtToken = new JLabel(Strings.enterJWT);
		lblPasteJwtToken.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPasteJwtToken = new GridBagConstraints();
		gbc_lblPasteJwtToken.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPasteJwtToken.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasteJwtToken.gridx = 1;
		gbc_lblPasteJwtToken.gridy = 1;
		add(lblPasteJwtToken, gbc_lblPasteJwtToken);

		creditButton = new JButton("About");
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JLabelLink jLabelLink = new JLabelLink(Strings.creditTitle, 530, 550);

				jLabelLink.addText("<h2>About JWT4B</h2>JSON Web Tokens (also known as JWT4B) is developed by Oussama Zgheb and Matthias Vetsch.<br>");
				jLabelLink.addURL("<a href=\"https://zgheb.com/\">Mantainer Website</a>","zgheb.com");
				jLabelLink.addURL("<a href=\"https://github.com/mvetsch/JWT4B\">GitHub Repository</a>","github.com/mvetsch/JWT4B");
				jLabelLink.addText("<br><br>");
				jLabelLink.addText("JWT4B, excluding the libraries mentioned below and the Burp extender classes, uses the GPL 3 license.");
				jLabelLink.addURL("* <a href=\"https://github.com/bobbylight/RSyntaxTextArea/blob/master/src/main/dist/RSyntaxTextArea.License.txt\">RSyntaxTextArea</a>","github.com/bobbylight/RSyntaxTextArea");
				jLabelLink.addURL("* <a href=\"https://github.com/auth0/java-jwt/blob/master/LICENSE\">Auth0 -java-jwt</a>","github.com/auth0/java-jwt");
				jLabelLink.addURL("* <a href=\"https://www.apache.org/licenses/\">Apache Commons Lang</a>","apache.org");
				jLabelLink.addText("<br><br>");
				jLabelLink.addText("Thanks to Compass Security AG for providing development time for the initial version<br>");
				jLabelLink.addURL("<a href=\"https://www.compass-security.com\">compass-security.com</a><br>","compass-security.com");
				jLabelLink.addText("and to Brainloop for providing broader token support!");
				jLabelLink.addURL("<a href=\"https://www.brainloop.com\">brainloop.com</a><br><br>","brainloop.com");

				jLabelLink.addLogoImage();
			}
		});
		GridBagConstraints gbc_creditButton = new GridBagConstraints();
		gbc_creditButton.insets = new Insets(0, 0, 5, 0);
		gbc_creditButton.gridx = 2;
		gbc_creditButton.gridy = 1;
		add(creditButton, gbc_creditButton);

		jwtInputField = new JTextArea();
		jwtInputField.setRows(2);
		jwtInputField.setLineWrap(true);
		jwtInputField.setWrapStyleWord(true);

		GridBagConstraints gbc_jwtInputField = new GridBagConstraints();
		gbc_jwtInputField.insets = new Insets(0, 0, 5, 5);
		gbc_jwtInputField.fill = GridBagConstraints.BOTH;
		gbc_jwtInputField.gridx = 1;
		gbc_jwtInputField.gridy = 2;
		add(jwtInputField, gbc_jwtInputField);

		lblEnterSecret = new JLabel(Strings.enterSecretKey);
		lblEnterSecret.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblEnterSecret = new GridBagConstraints();
		gbc_lblEnterSecret.anchor = GridBagConstraints.WEST;
		gbc_lblEnterSecret.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterSecret.gridx = 1;
		gbc_lblEnterSecret.gridy = 3;
		add(lblEnterSecret, gbc_lblEnterSecret);

		jwtKeyArea = new JTextArea();
		GridBagConstraints gbc_jwtKeyField = new GridBagConstraints();
		gbc_jwtKeyField.insets = new Insets(0, 0, 5, 5);
		gbc_jwtKeyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jwtKeyField.gridx = 1;
		gbc_jwtKeyField.gridy = 4;
		add(jwtKeyArea, gbc_jwtKeyField);
		jwtKeyArea.setColumns(10);

		jwtSignatureButton = new JButton("");
		Dimension preferredSize = new Dimension(400, 30);
		jwtSignatureButton.setPreferredSize(preferredSize);

		GridBagConstraints gbc_jwtSignatureButton = new GridBagConstraints();
		gbc_jwtSignatureButton.insets = new Insets(0, 0, 5, 5);
		gbc_jwtSignatureButton.gridx = 1;
		gbc_jwtSignatureButton.gridy = 6;
		add(jwtSignatureButton, gbc_jwtSignatureButton);

		GridBagConstraints gbc_jwtOuputField = new GridBagConstraints();
		gbc_jwtOuputField.insets = new Insets(0, 0, 5, 5);
		gbc_jwtOuputField.fill = GridBagConstraints.BOTH;
		gbc_jwtOuputField.gridx = 1;
		gbc_jwtOuputField.gridy = 9;

		jwtOuputField = new RSyntaxTextArea();
		SyntaxScheme scheme = jwtOuputField.getSyntaxScheme();
		Style style = new Style();
		style.foreground = new Color(222, 133, 10);
		scheme.setStyle(Token.LITERAL_STRING_DOUBLE_QUOTE, style);
		jwtOuputField.revalidate();
		jwtOuputField.setHighlightCurrentLine(false);
		jwtOuputField.setCurrentLineHighlightColor(Color.WHITE);
		jwtOuputField.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		jwtOuputField.setEditable(false);
		// no context menu on right-click
		jwtOuputField.setPopupMenu(new JPopupMenu());
		RTextScrollPane sp = new RTextScrollPane(jwtOuputField);
		sp.setLineNumbersEnabled(false);

		lblExtendedVerificationInfo = new JLabel("");
		GridBagConstraints gbc_lblExtendedVerificationInfo = new GridBagConstraints();
		gbc_lblExtendedVerificationInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblExtendedVerificationInfo.gridx = 1;
		gbc_lblExtendedVerificationInfo.gridy = 7;
		add(lblExtendedVerificationInfo, gbc_lblExtendedVerificationInfo);

		JLabel lblDecodedJwt = new JLabel(Strings.decodedJWT);
		lblDecodedJwt.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDecodedJwt = new GridBagConstraints();
		gbc_lblDecodedJwt.anchor = GridBagConstraints.WEST;
		gbc_lblDecodedJwt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDecodedJwt.gridx = 1;
		gbc_lblDecodedJwt.gridy = 8;
		add(lblDecodedJwt, gbc_lblDecodedJwt);

		add(sp, gbc_jwtOuputField);

		lbRegisteredClaims = new JLabel();
		lbRegisteredClaims.setBackground(new Color(238, 238, 238));
		GridBagConstraints gbc_lbRegisteredClaims = new GridBagConstraints();
		gbc_lbRegisteredClaims.fill = GridBagConstraints.BOTH;
		gbc_lbRegisteredClaims.insets = new Insets(0, 0, 5, 5);
		gbc_lbRegisteredClaims.gridx = 1;
		gbc_lbRegisteredClaims.gridy = 11;
		add(lbRegisteredClaims, gbc_lbRegisteredClaims);

	}

	public String getJWTInput() {
		return jwtInputField.getText();
	}

	public String getKeyInput() {
		return jwtKeyArea.getText();
	}
}
