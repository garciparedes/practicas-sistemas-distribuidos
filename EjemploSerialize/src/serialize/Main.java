package serialize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {

		TestClass obj = new TestClass();

		obj.setAge(22);
		obj.setName("Dave");

		FileOutputStream f_out;
		
		ObjectOutputStream obj_out;
		try {
			f_out = new FileOutputStream("mis_objetosTest.obd");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
