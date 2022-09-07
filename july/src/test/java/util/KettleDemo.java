package util;

import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.net.URL;
import java.util.Map;
import java.util.UUID;


/**
 * https://mkyong.com/java/java-read-a-file-from-resources-folder/
 */
@Slf4j
public class KettleDemo {


	public static void main(String[] args) {

		URL resource = KettleDemo.class.getClassLoader().getResource("BS_DEMO.ktr");
		log.info("file path: {}", resource.getPath());
		runKettleTransfer(null, resource.getPath());

	}

	/**
	 * 执行作业
	 *
	 * @param initKettleParam
	 * @param kjbFilePath
	 * @return
	 */
	/*public static boolean runKettleJob(Map<String, String> initKettleParam, String kjbFilePath) {
		String uuid = UUID.randomUUID().toString();
		log.info("ExecKettleUtil@runKettleJob:" + uuid + " {kjbFilePath:" + kjbFilePath + "}");
		try {
			KettleEnvironment.init();
			// 初始化job路径
			JobMeta jobMeta = new JobMeta(kjbFilePath, null);
			Job job = new Job(null, jobMeta);
			// 初始化job参数，脚本中获取参数值：${variableName}
			if (initKettleParam != null) {
				for (String variableName : initKettleParam.keySet()) {
					job.setVariable(variableName, initKettleParam.get(variableName));
				}
			}

			job.start();
			job.waitUntilFinished();
			if (job.getErrors() > 0) {
				log.info("ExecKettleUtil@runKettleJob:" + uuid + " 执行失败");
			} else {
				log.info("ExecKettleUtil@runKettleJob:" + uuid + " 执行成功");
			}
			return true;
		} catch (Exception e) {
			log.error("ExecKettleUtil@runKettleJob:" + uuid, e);
			return false;
		}
	}*/

	/**
	 * 执行转换
	 * @param initKettleParam
	 * @param ktrFilePath
	 * @return
	 */
	public static boolean runKettleTransfer(Map<String, String> initKettleParam, String ktrFilePath) {
		Trans trans = null;
		String uuid = UUID.randomUUID().toString();
		log.info("ExecKettleUtil@runKettleTransfer:" + uuid + " {ktrFilePath:" + ktrFilePath + "}");
		try {
			// 初始化
			KettleEnvironment.init();
			EnvUtil.environmentInit();
			TransMeta transMeta = new TransMeta(ktrFilePath);
			// 转换
			trans = new Trans(transMeta);
			// 初始化trans参数，脚本中获取参数值：${variableName}
			if (initKettleParam != null) {
				for (String variableName : initKettleParam.keySet()) {
					trans.setVariable(variableName, initKettleParam.get(variableName));
				}
			}

			// 执行转换
			trans.execute(null);
			// 等待转换执行结束
			trans.waitUntilFinished();
			if (trans.getErrors() > 0) {
				log.info("ExecKettleUtil@runKettleTransfer:" + uuid + " 执行失败");
			} else {
				log.info("ExecKettleUtil@runKettleTransfer:" + uuid + " 执行成功");
			}
			return true;
		} catch (Exception e) {
			log.error("ExecKettleUtil@runKettleTransfer:" + uuid, e);
			return false;
		}
	}

}
