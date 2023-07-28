package p230609;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Main extends WindowAdapter implements ActionListener {
	private Frame f, f2;
	private TextField tfId, tfPwd, tfMsg;
	private Button bLogin;
	private MemberDAO dao;
	private EndodeingDecoding ed;
	
	public Main() {
		ed = new EndodeingDecoding();
		f = new Frame("Login Frame");
		f2 = new Frame("Login Success");
		f.setSize(500,300);
		f2.setSize(500,300);
		f.setLayout(null);
		
		Label lid = new Label("    ID");
		lid.setBounds(50, 50, 40, 40);
		
		tfId = new TextField();
		tfId.setBounds(150, 60, 200, 20);
		
		Label lpwd = new Label("    Password");
		lpwd.setBounds(50, 80, 80, 40);
		
		tfPwd = new TextField();
		tfPwd.setBounds(150, 95, 200, 20);
		tfPwd.setEchoChar('*');
		
		bLogin  = new Button("Login");
		bLogin.setBounds(300, 130, 50, 20);
		bLogin.addActionListener(this);
		
		tfMsg = new TextField();
		tfMsg.setBounds(60, 200, 295, 40);
		tfMsg.setEditable(false);
		tfMsg.setFocusable(false);
		dao = new MemberDAO();
		
		f.add(lid);
		f.add(tfId);
		f.add(lpwd);
		f.add(tfPwd);
		f.add(bLogin);
		f.add(tfMsg);
		f.addWindowListener(this);
		f2.addWindowListener(this);
		f.setVisible(true);
	}
	
	
	public void windowClosing(WindowEvent e) {
		if (e.getComponent().getName().equals("frame0")) {
			f2.dispose();
		} else {
//			System.exit(0);
			f.dispose();
		}
	}
	
	
	public static void main(String[] args) {
		Main m = new Main();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(tfId.getText() + tfPwd.getText());

		String inpid = tfId.getText();
		ArrayList<MemberVo> list = dao.list(inpid);

		if (list.size() == 1) {
			MemberVo data = (MemberVo) list.get(0);
			String id = data.getId();
			String pwd = data.getPassword();
			
			System.out.println(id + " : " + pwd);
			try {
				SecretKey key = EndodeingDecoding.getKey();
				IvParameterSpec ivParameterSpec = EndodeingDecoding.getIv();
				String specName = "AES/CBC/PKCS5Padding";

				String encryptedText = EndodeingDecoding.encrypt(specName, key, ivParameterSpec, pwd);
				String decryptedText = EndodeingDecoding.decrypt(specName, key, ivParameterSpec, encryptedText);
				System.out.println("cipherText: " + encryptedText);
				System.out.println("plainText: " + decryptedText);
				
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			
			if (tfPwd.getText().equals(pwd)) {
				tfMsg.setText("로그인이 되었습니다!");
				f2.setBounds(200,200,400,400);
				f2.setVisible(true);
			} else {
				tfMsg.setText("다시 입력하세요.");
			}
		} else {
			tfMsg.setText("다시 입력하세요.");
		}
	}
}
