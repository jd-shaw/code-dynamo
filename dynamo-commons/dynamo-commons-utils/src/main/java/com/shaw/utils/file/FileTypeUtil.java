package com.shaw.utils.file;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import com.shaw.utils.text.StringUtils;

/**
 * @author xjd
 * @date 2023/6/27
 */
public class FileTypeUtil {

	private static final CharSequence[] SPECIAL_SUFFIX = new CharSequence[] { "tar.bz2", "tar.Z", "tar.gz", "tar.xz" };
	private static final Map<String, String> FILE_TYPE_MAP = new ConcurrentSkipListMap();

	public FileTypeUtil() {
	}

	public static String putFileType(String fileStreamHexHead, String extName) {
		return (String) FILE_TYPE_MAP.put(fileStreamHexHead, extName);
	}

	public static String removeFileType(String fileStreamHexHead) {
		return (String) FILE_TYPE_MAP.remove(fileStreamHexHead);
	}

	public static String getType(String filename) {
		String typeName = extName(filename);
		if (StringUtils.isNotBlank(typeName)) {
			return typeName;
		} else if (typeName.endsWith("docx")) {
			return "docx";
		} else if (typeName.endsWith("xlsx")) {
			return "xlsx";
		} else if (typeName.endsWith("pptx")) {
			return "pptx";
		} else if (typeName.endsWith("jar")) {
			return "jar";
		} else if (typeName.endsWith("war")) {
			return "war";
		} else if (typeName.endsWith("ofd")) {
			return "ofd";
		}
		return typeName;
	}

	public static String extName(String fileName) {
		if (fileName == null) {
			return null;
		} else {
			int index = fileName.lastIndexOf(".");
			if (index == -1) {
				return "";
			} else {
				int secondToLastIndex = fileName.substring(0, index).lastIndexOf(".");
				String substr = fileName.substring(secondToLastIndex == -1 ? index : secondToLastIndex + 1);
				if (StringUtils.containsAny(substr, SPECIAL_SUFFIX)) {
					return substr;
				} else {
					String ext = fileName.substring(index + 1);
					return StringUtils.containsAny(ext, '/', '\\') ? "" : ext;
				}
			}
		}
	}
}
