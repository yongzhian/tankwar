package com.zain.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.zain.model.wall.BrickWall;
import com.zain.model.wall.GrassWall;
import com.zain.model.wall.IronWall;
import com.zain.model.wall.RiverWall;
import com.zain.model.wall.Wall;
import com.zain.type.WallType;

/**
 * ��ͼ���ݹ�����
 * 
 * @author www.mingrisoft.com
 *
 */

public class MapIO {
	// ��ͼ�����ļ�·��
	public final static String DATA_PATH = "map/data/";
	// ��ͼԤ��ͼ·��
	public final static String IMAGE_PATH = "map/image/";
	// ��ͼ�����ļ���׺
	public final static String DATA_SUFFIX = ".map";
	// ��ͼԤ��ͼ��׺
	public final static String IMAGE_SUFFIX = ".jpg";
	/**
	 * ��ȡָ�����Ƶ�ͼ������ǽ�鼯��
	 *
	 * @param mapName
	 * @return
	 */
	public static List<Wall> readMap(String mapName) {
		// ������Ӧ���Ƶĵ�ͼ�ļ�
		File file = new File(DATA_PATH + mapName + DATA_SUFFIX);
		return readMap(file);// �������ط���
	}

	/**
	 *
	 * ��ȡ��ͼ�ļ�������ǽ�鼯��
	 *
	 * @param file
	 *            - ��ͼ�ļ�
	 * @return �˵�ͼ�е�����ǽ�鼯��
	 */
	public static List<Wall> readMap(File file) {
		Properties pro = new Properties();// �������Լ�����
		List<Wall> walls = new ArrayList<>();// ������ǽ�鼯��
		try {
			pro.load(new FileInputStream(file));// ���Լ������ȡ��ͼ�ļ�
			String brickStr = (String) pro.get(WallType.brick.name());// ��ȡ��ͼ�ļ���שǽ�������Ե��ַ�������
			String grassStr = (String) pro.get(WallType.grass.name());// ��ȡ��ͼ�ļ��вݵ��������Ե��ַ�������
			String riverStr = (String) pro.get(WallType.river.name());// ��ȡ��ͼ�ļ��к����������Ե��ַ�������
			String ironStr = (String) pro.get(WallType.iron.name());// ��ȡ��ͼ�ļ�����ǽ�������Ե��ַ�������
			if (brickStr != null) {// �����ȡ��שǽ���ݲ���null
				walls.addAll(readWall(brickStr, WallType.brick));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
			}
			if (grassStr != null) {// �����ȡ�Ĳݵ����ݲ���null
				walls.addAll(readWall(grassStr, WallType.grass));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
			}
			if (riverStr != null) {// �����ȡ�ĺ������ݲ���null
				walls.addAll(readWall(riverStr, WallType.river));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
			}
			if (ironStr != null) {// �����ȡ����ǽ���ݲ���null
				walls.addAll(readWall(ironStr, WallType.iron));// �������ݣ����������н�������ǽ�鼯����ӵ���ǽ�鼯����
			}
			return walls;// ������ǽ�鼯��
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����ǽ������
	 * 
	 * @param data
	 *            - ǽ�����������ַ���
	 * @param type
	 *            - ǽ������
	 * @return ǽ�鼯��
	 */
	private static List<Wall> readWall(String data, WallType type) {
		String walls[] = data.split(";");// ʹ�á�;���ָ��ַ���
		Wall wall;// ����ǽ�����
		List<Wall> w = new LinkedList<>();// ����ǽ�鼯��
		switch (type) {// �ж�̹������
		case brick:// �����שǽ
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new BrickWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ���שǽ����
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case river:// ����Ǻ���
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new RiverWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����������
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case grass:// ����ǲݵ�
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new GrassWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ����ݵض���
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		case iron:// �������ǽ
			for (String wStr : walls) {// �����ָ���
				String axes[] = wStr.split(",");// ʹ�á�,���ָ��ַ���
				// ����ǽ����󣬷ָ�ĵ�һ��ֵΪ�����꣬�ָ�ĵڶ���ֵΪ������
				wall = new IronWall(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));// �ڴ������ϴ�����ǽ����
				w.add(wall);// ��������Ӵ�ǽ��
			}
			break;
		}
		return w;// ����ǽ�鼯��
	}
}
