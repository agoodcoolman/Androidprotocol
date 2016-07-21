package com.example.protocoltest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.example.tutorial.AddressBookProtos;
import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.AddressBook.Builder;
import com.example.tutorial.AddressBookProtos.Person;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.http.client.entity.FileUploadEntity;
import com.lidroid.xutils.http.client.multipart.FormBodyPart;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.MultipartEntity.CallBackInfo;
import com.lidroid.xutils.http.client.multipart.content.ByteArrayBody;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.util.MimeTypeUtils;
import com.proto.demo.PersonInfo;
import com.proto.demo.PersonInfo.Facephoto;
import com.proto.demo.Result.result;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void button(View v) {
		Person person = Person.newBuilder()
		.setId(1)
		.setEmail("agoodcoolman@gamil.com")
		.setName("whta")
		.build();
		
//		AddressBook addressBook = AddressBook.newBuilder().addPerson(person).build();
		com.proto.demo.PersonInfo.Person personinfo = null;
		
		String filePath = Environment.getExternalStorageDirectory().getAbsoluteFile().toString()+"/AndroidWT/face/face.jpg";
		FileInputStream fileInputStream;
		
		try {
			if (new File(filePath).exists()) {
				fileInputStream = new FileInputStream(filePath);
				com.proto.demo.PersonInfo.Facephoto.Builder mergeFrom = Facephoto.newBuilder().setPhoto(ByteString.readFrom(fileInputStream)).setDescribe("棒棒的");
				
				personinfo = PersonInfo.Person.newBuilder().setId(1).addPhoto(mergeFrom).build();
				
				int id = personinfo.getId();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		/*if(!file.exists()) {
			file.mkdirs();
		}
		
		File file2 = new File(file, "ts.txt");
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		HttpUtils httpUtils = new HttpUtils();
//		FileBody fileBody = new FileBody(new File(externalStorageDirectory), MimeTypeUtils.getMimeType(externalStorageDirectory));
		RequestParams requestParams = new RequestParams();
//		requestParams.addBodyParameter("file", file);
		MultipartEntity multipartEntity = new MultipartEntity();
		
//		FileBody fileBody = new FileBody(file2);
		byte[] byteArray = personinfo.toByteArray();
		ByteArrayBody byteArrayBody = new ByteArrayBody(byteArray, "file");
//		multipartEntity.addPart("file", fileBody);
		multipartEntity.addPart("file", byteArrayBody);
		requestParams.setBodyEntity(multipartEntity);
		httpUtils.send(HttpMethod.POST, "http://192.168.1.5:8080/Auth2/protobuf.do?", requestParams, new RequestCallBack<String>() {


			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				try {
					String results= arg0.result;
					result parseFrom = result.parseFrom(results.getBytes());
					System.out.println(parseFrom.toString());
				} catch (InvalidProtocolBufferException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				
				
			}
		});
	}
}
