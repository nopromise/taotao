import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {
	@Test
	public void testFreeMarker() throws Exception {

		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configration对象
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

		// 第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(
				new File("F:\\eclipse-oxygen-workspace\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate("second.ftl");
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的第七步：创建模板需要的数据集，可以是一个map对象【常用map】，也可以是pojoi。把模板需要的数据都放入数据集。
		Map<Object, Object> root = new HashMap<>();
		//root.put("title", "hello freemarker");
		Student student = new Student(121, "马佳星", "威海");
		root.put("student", student);
		List stuList = new ArrayList<Student>();
		stuList.add(new Student(101, "mjx1", "xian1"));
		stuList.add(new Student(102, "mjx2", "xian2"));
		stuList.add(new Student(103, "mjx3", "xian3"));
		root.put("students", stuList);

		List nameList = new ArrayList<>();
		nameList.add("马佳星");
		nameList.add("张优优");
		nameList.add("董晓晓");
		root.put("names", nameList);
		
		String [] ballArr=new String[10];
		ballArr[0]="football";
		ballArr[1]="baseball";
		ballArr[2]="basketball";
		ballArr[3]="football";
		ballArr[4]="football";
		ballArr[5]="baseball";
		ballArr[6]="basketball";
		ballArr[7]="football";
		ballArr[8]="baseball";
		ballArr[9]="basketball";
		root.put("balls", ballArr);
		root.put("hello", "hello weihai");
	//	root.put("curdate", new Date());
		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File("D:\\temp\\html\\second.html"));
		// 第九步：调用模板对象的process 方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();

	}

	public class Student {
		private int id;
		private String name;
		private String address;

		public Student(int id, String name, String address) {
			super();
			this.id = id;
			this.name = name;
			this.address = address;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}
}
