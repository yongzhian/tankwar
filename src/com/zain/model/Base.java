package com.zain.model;

import com.zain.model.wall.Wall;
import com.zain.util.ImageUtil;

/**
 * ����
 * 
 * @author www.mingrisoft.com
 *
 */
public class Base extends Wall {
	/**
	 * ���ع��췽��
	 * 
	 * @param x
	 *            - ���غ�����
	 * @param y
	 *            - ����������
	 */
	public Base(int x, int y) {
		super(x, y, ImageUtil.BASE_IMAGE_URL);// ���ø��๹�췽����ʹ��Ĭ�ϻ���ͼƬ
	}

}
