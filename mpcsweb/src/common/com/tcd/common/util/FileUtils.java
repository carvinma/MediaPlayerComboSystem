package com.tcd.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtils {
	static final int BUFFER = 2048;
	static final String ZIP = "504B0304";
	static final String RAR = "52617221";

	public static boolean isZipFile(FileInputStream fileInputStream) {
		return ZIP.equalsIgnoreCase(bytesToHexString(fileInputStream));
	}

	/**
	 * 查看文件格式
	 * 
	 * @param fileInputStream
	 * @return String文件前几个字节
	 */
	private static String bytesToHexString(FileInputStream fileInputStream) {
		byte[] b = new byte[4];
		try {
			fileInputStream.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 解压zip格式的压缩文件到指定位置
	 * 
	 * @param fileName
	 * 
	 * @param filePath压缩文件路径
	 * @param unZipDir解压缩文件存放路径
	 */
	@SuppressWarnings("rawtypes")
	public static boolean unZip(ZipFile zipFile, String unZipDir) {
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			Enumeration enu = zipFile.entries();

			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

				if (zipEntry.isDirectory()) {
					new File(unZipDir + "/" + zipEntry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
				File file = new File(unZipDir + "/" + zipEntry.getName());
				File parent = file.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, BUFFER);

				byte[] array = new byte[BUFFER];
				while (bis.read(array, 0, BUFFER) != -1) {
					bos.write(array, 0, BUFFER);
				}

				bos.flush();

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 * @return dirFilePath文件路径
	 */
	public static String saveFile(File file, String dirFilePath, String fileName) {

		FileOutputStream fos = null;
		FileInputStream fis = null;
		String filePath = null;
		try {
			filePath = dirFilePath + "/" + fileName;
			File dirFile = new File(dirFilePath);
			if (!dirFile.exists())
				dirFile.mkdirs();
			// 建立文件输出流
			fos = new FileOutputStream(filePath);
			// 建立文件上传流
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("FileInputStream关闭失败");
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("FileOutputStream关闭失败");
					e.printStackTrace();
				}
			}
		}
		return dirFilePath + "/" + fileName;
	}

	/**
	 * 按行读取文件并保存文件
	 * 
	 * @param file
	 * @return dirFilePath文件路径
	 */
	public static String saveFileLine(File file, String dirFilePath, String fileName) {

		FileReader fileReader = null;
		FileWriter fileWriter = null;
		BufferedReader reader = null;

		try {
			fileReader = new FileReader(file);
			File dir = new File(dirFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			fileWriter = new FileWriter(dirFilePath + "/" + fileName, true);

			reader = new BufferedReader(fileReader);

			fileWriter.write("\n");
			String inLine = null;
			while ((inLine = reader.readLine()) != null) {
				fileWriter.write(inLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return dirFilePath + "/" + fileName;
	}

	/**
	 * 合并文件内容
	 * 
	 * @param newFilePath
	 * @param oldFilePath
	 * @return boolean
	 */
	public static boolean margeFile(String newFilePath, String oldFilePath) {
		File newFile = new File(newFilePath);
		// 遍历新文件
		for (File file : newFile.listFiles()) {
			// System.out.println(file.getName());
			if (file.isDirectory()) {
				// 遍历数据文件
				for (File child : file.listFiles()) {
					// System.out.println(child.getName());
					File oldFile = new File(oldFilePath + "/" + file.getName() + "/" + child.getName());
					// 判断该文件是否已上传

					if (oldFile.exists() && !isSame(child, oldFile)) {
						if (saveFileLine(child, oldFilePath + "/" + file.getName() + "/", child.getName()) == null)
							return false;
						continue;
					}
					// 增加未上传的文件
					String path = saveFile(child, oldFilePath + "/" + file.getName() + "/", child.getName());
					if (path == null)
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断两个文件是否一样
	 * 
	 * @param newFile
	 * @param oldFile
	 * @return boolean
	 */
	private static boolean isSame(File newFile, File oldFile) {
		if (newFile.length() == 0 && oldFile.length() == 0)
			return true;
		if (newFile.length() != oldFile.length())
			return false;
		FileReader fis1 = null;
		FileReader fis2 = null;
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		try {
			fis1 = new FileReader(newFile);
			fis2 = new FileReader(oldFile);
			reader1 = new BufferedReader(fis1);
			reader2 = new BufferedReader(fis2);
			// 读取文件第一行 判断是否一样
			if (reader1.readLine().equals(reader2.readLine())) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 关闭文件流，防止内存泄漏
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) { // 忽略
					e.printStackTrace();
				}
			}
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e) { // 忽略
					e.printStackTrace();
				}
			}
			if (fis1 != null) {
				try {
					fis1.close();
				} catch (IOException e) { // 忽略
					e.printStackTrace();
				}
			}
			if (fis2 != null) {
				try {
					fis2.close();
				} catch (IOException e) { // 忽略
					e.printStackTrace();
				}
			}

		}
		return true;
	}

	/**
	 * 压缩文件到指定的目录
	 * 
	 * @param inputFilename
	 * @param zipFilename
	 * @throws IOException
	 */
	public static void zip(String inputFilename, String zipFilename) throws IOException {
		zip(new File(inputFilename), zipFilename);
	}

	/**
	 * 压缩文件到指定的目录
	 * 
	 * @param inputFile
	 * @param zipFilename
	 * @throws IOException
	 */
	public static void zip(File inputFile, String zipFilename) throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));
		try {
			zip(inputFile, out, "");
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
		}
	}

	private static void zip(File inputFile, ZipOutputStream out, String base) throws IOException {
		if (inputFile.isDirectory()) {
			File[] inputFiles = inputFile.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < inputFiles.length; i++) {
				zip(inputFiles[i], out, base + inputFiles[i].getName());
			}

		} else {
			if (base.length() > 0) {
				out.putNextEntry(new ZipEntry(base));
			} else {
				out.putNextEntry(new ZipEntry(inputFile.getName()));
			}

			FileInputStream in = new FileInputStream(inputFile);
			try {
				int c;
				byte[] by = new byte[1024];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				in.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		//

		// FileUtils.saveFile(new File("c:/201300009.zip"),
		// "c:/data/201300009","201300009.zip");
		// FileUtils.unZip(new ZipFile("c:/data/201300009/201300009.zip"),
		// "c:/data/201300009");
		// FileUtils.margeFile("c:/data/temp/201300009",
		// "c:/data/201300009/201300009");
		FileUtils.zip("c:/data/201300009/201300009", "c:/data/201300009/201300009.zip");
	}
}
