package com.zain.model.wall;

import com.zain.util.ImageUtil;

/**
 * שǽ
 * 
 * @author www.mingrisoft.com
 *
 */
public class BrickWall extends Wall {
	/**
	 * שǽ���췽��
	 * 
	 * @param x
	 *            - ��ʼ��������
	 * @param y
	 *            - ��ʼ��������
	 */
	public BrickWall(int x, int y) {
		super(x, y, ImageUtil.BRICKWALL_IMAGE_URL);// ���ø��๹�췽����ʹ��Ĭ��שǽͼƬ
	}

}
