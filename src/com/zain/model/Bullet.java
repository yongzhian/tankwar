package com.zain.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.zain.frame.GamePanel;
import com.zain.model.wall.BrickWall;
import com.zain.model.wall.IronWall;
import com.zain.model.wall.Wall;
import com.zain.type.Direction;
import com.zain.type.TankType;

/**
 * 子弹
 * 
 * @author www.mingrisoft.com
 *
 */
public class Bullet extends VisibleImage {
	Direction direction;
	static final int LENGTH = 8;// 子弹的（正方体）边长
	private GamePanel gamePanel;// 游戏面板
	private int speed = 7;// 移动速度
	private boolean alive = true;// 子弹是否存活（有效）
	Color color = Color.ORANGE;// 子弹颜色.橙色
	TankType owner;// 发出子弹的坦克类型

	/**
	 * 
	 * 子弹构造方法
	 * 
	 * @param x
	 *            - 子弹的初始横坐标
	 * @param y
	 *            - 子弹初始纵坐标
	 * @param direction
	 *            - 子弹发射方向
	 * @param gamePanel
	 *            - 游戏面板对象
	 * @param owner
	 *            - 发出子弹的坦克类型
	 */
	public Bullet(int x, int y, Direction direction, GamePanel gamePanel, TankType owner) {
		super(x, y, LENGTH, LENGTH);// 调用父类构造方法
		this.direction = direction;
		this.gamePanel = gamePanel;
		this.owner = owner;
		init();// 初始化组件
	}

	/**
	 * 初始化组件
	 */
	private void init() {
		Graphics g = image.getGraphics();// 获取图片的绘图方法
		g.setColor(Color.WHITE);// 使用白色绘图
		g.fillRect(0, 0, LENGTH, LENGTH);// 绘制一个铺满整个图片的白色实心矩形
		g.setColor(color);// 使用子弹颜色
		g.fillOval(0, 0, LENGTH, LENGTH);// 绘制一个铺满整个图片的实心圆形
		g.setColor(Color.BLACK);// 使用黑色
		g.drawOval(0, 0, LENGTH - 1, LENGTH - 1);// 给圆形绘制一个黑色的边框，防止绘出界，宽高减小1像素
	}

	/**
	 * 子弹移动
	 */
	public void move() {
		switch (direction) {// 判断移动方向
		case UP:// 如果向上
			upward();// 向上移动
			break;
		case DOWN:// 如果向下
			downward();// 向下移动
			break;
		case LEFT:// 如果向左
			leftward();// 向左移动
			break;
		case RIGHT:// 如果向右
			rightward();// 向右移动
			break;
		}
	}

	/**
	 * 向左移动
	 */
	private void leftward() {
		x -= speed;// 横坐标减少
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向右移动
	 */
	private void rightward() {
		x += speed;// 横坐标增加
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向上移动
	 */
	private void upward() {
		y -= speed;// 总坐标减少
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 向下移动
	 */
	private void downward() {
		y += speed;// 纵坐标增加
		moveToBorder();// 移动出面板边界时销毁子弹
	}

	/**
	 * 击中坦克
	 */
	public void hitTank() {
		List<Tank> tanks = gamePanel.getTanks();// 获取所有坦克的集合
		for (int i = 0, lengh = tanks.size(); i < lengh; i++) {// 遍历坦克集合
			Tank t = tanks.get(i);// 获取坦克对象啊你给
			if (t.isAlive() && this.hit(t)) {// 如果坦克是存货的并且子弹击中了坦克
				switch (owner) {// 判断子弹属于什么坦克
				case player1:// 如果是玩家1的子弹，效果同下
				case player2:// 如果是玩家2的子弹
					if (t instanceof Bot) {// 如果击中的坦克是电脑
						alive = false;// 子弹销毁
						t.setAlive(false);// 电脑坦克阵亡
					} else if (t instanceof Tank) {// 如果击中的是玩家
						alive = false;// 子弹销毁
					}
					break;
				case bot:// 如果是电脑的子弹
					if (t instanceof Bot) {// 如果击中的坦克是电脑
						alive = false;// 子弹销毁
					} else if (t instanceof Tank) {// 如果击中的是玩家
						alive = false;// 子弹销毁
						t.setAlive(false);// 玩家坦克阵亡
					}
					break;
				default:// 默认
					alive = false;// 子弹销毁
					t.setAlive(false);// 坦克阵亡
				}
			}
		}
	}

	/**
	 * 击中基地
	 */
	public void hitBase() {
		Base b = gamePanel.getBase();// 获取基地
		if (this.hit(b)) {// 如果子弹击中基地
			alive = false;// 子弹销毁
			b.setAlive(false);// 基地阵亡
		}
	}

	/**
	 * 击中墙块
	 */
	public void hitWall() {
		List<Wall> walls = gamePanel.getWalls();// 获取所有墙块
		for (int i = 0, lengh = walls.size(); i < lengh; i++) {// 遍历所有墙块
			Wall w = walls.get(i);// 获取墙块对象
			if (this.hit(w)) {// 如果子弹击中墙块
				if (w instanceof BrickWall) {// 如果是砖墙
					alive = false;// 子弹销毁
					w.setAlive(false);// 砖墙销毁
				}
				if (w instanceof IronWall) {// 如果是钢铁
					alive = false;// 子弹销毁
				}
			}
		}
	}

	/**
	 * 移动出面板边界时销毁子弹
	 */
	private void moveToBorder() {
		if (x < 0 || x > gamePanel.getWidth() - getWidth() || y < 0 || y > gamePanel.getHeight() - getHeight()) {// 如果子弹坐标离开游戏面板
			dispose();// 销毁子弹
		}
	}

	/**
	 * 销毁子弹
	 */
	private synchronized void dispose() {
		alive = false;// 存活（有效）状态变为false
	}

	/**
	 * 获取子弹存活状态
	 * 
	 * @return
	 */
	public boolean isAlive() {
		return alive;
	}
}
