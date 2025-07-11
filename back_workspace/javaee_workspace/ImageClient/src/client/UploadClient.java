package client;

import java.awt.FlowLayout;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class UploadClient extends JFrame {

	JTextField t_title;
	JFileChooser chooser;
	JButton bt_file; // 파일 선택 버튼
	JButton bt_regist;

	File[] files; // 유저가 선택한 파일

	public UploadClient() {
		t_title = new JTextField(15);
		bt_file = new JButton("파일선택");
		bt_regist = new JButton("업로드");
		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true); // 여러 파일 선택 옵션 추가

		// flow로 부착
		setLayout(new FlowLayout());
		add(t_title);
		add(bt_file);
		add(bt_regist);

		// 파일선택
		bt_file.addActionListener((e) -> {
			int result = chooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {
				files = chooser.getSelectedFiles();
			}
		});

		// 파일 업로드
		bt_regist.addActionListener((e) -> {
			upload();
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 170);
		setVisible(true);
	}

	// 유저가 선책한 파일 수만큼 반복하면서 서버로 바이너리 파일을 전송
	public void upload() {
		// http 통신이 가능한 api 이용
		// 1) HttpURLConnection 객체 (고전)
		// 2) HttpClient 객체 (최신)

		CloseableHttpClient httpClient = HttpClients.createDefault();

		// post
		HttpPost post = new HttpPost("http://192.168.60.21:8888/upload/regist");

		/**** 서버로 전송할 데이터 구성 ****/
		StringBody titleBody = new StringBody(t_title.getText(), ContentType.create("text/plain", Consts.UTF_8));

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addPart("title", titleBody); // 텍스트 파라미터 만들기. title은 html에서 name에 부여한 것과 동일한 값

		// 이미지 수만큼 반복하면서 빌더에 바이너리 채우기
		for (int i = 0; i < files.length; i++) {
			FileBody fileBody = new FileBody(files[i]);
			builder.addPart("photo" + i, fileBody);
		}

		// 완성된 http 파라미터와 값을 post 객체에 담기
		HttpEntity entity = builder.build(); // 전송 전 빌더를 엔터티로 변경
		post.setEntity(entity);

		// 서버에 요청 시도
		// 서버에서 전송한 Http Status Code를 반환받기
		CloseableHttpResponse response = null; // 서버가 전송한 상태 코드를 보유한 객
		try {
			// 웹브라우저로 요청
			httpClient.execute(post);

			int status = response.getStatusLine().getStatusCode(); // int형으로 된 응답 코드
			if (status == 200) { // 성공
				JOptionPane.showMessageDialog(this, "업로드 성공");
			} else {
				JOptionPane.showMessageDialog(this, "업로드 실패");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new UploadClient();
	}
}
