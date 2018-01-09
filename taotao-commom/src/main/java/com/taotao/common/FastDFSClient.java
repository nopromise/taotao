package com.taotao.common;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSClient {
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	// 使用StorageClient进行上传
	private StorageClient storageClient = null;

	public FastDFSClient(String conf) throws Exception {
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = trackerClient.getStoreStorage(trackerServer);
		storageClient = new StorageClient(trackerServer, storageServer);
	}

	public String uploadFile(String local_filename, String file_ext_name) throws Exception {
        local_filename="F:\\picture\\"+local_filename;
		String[] result = storageClient.upload_file(local_filename, file_ext_name, null);

		String res = "";
		for (String r : result) {
			res += r + "/";
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}
}