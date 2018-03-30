/**
 *
 */
package com.fkapi.utils;

import org.apache.commons.lang3.SystemUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.*;
import sun.plugin2.util.SystemUtil;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *         <p>
 *         <p>
 *         / + Enter: 给字段、方法、类添加java注释
 *         <p>
 *         CTRL + SHIFT + 上下方向键: 向上或向下移动当前行的代码
 *         <p>
 *         CTRL + P: 显示方法参数列表
 *         <p>
 *         CTRL + F12: 显示当前文件的成员列表
 *         <p>
 *         CTRL + SHIFT + E: 打开最近修改过的文件列表
 *         <p>
 *         CTRL + Y： 表示删除当前行
 *         <p>
 *         Ctrl+Shift+A：发号施令
 *         <p>
 *         Ctrl+J： 可以查看所有模板
 *         <p>
 *         SHIFT + F2 / F2: 移动到错误或者警告的行
 *         <p>
 *         CTRL + F1: 提示错误或者警告行的解释
 *         <p>
 *         CTRL + w: 选择单词，按多次选择范围变大
 *         <p>
 *         CTRL + SHIFT + R:表示全局搜索替换
 *         <p>
 *         CTRL + T:更新代码；
 *         <p>
 *         CTRL + K: 提交代码
 *         <p>
 *         CTRL + E: 打开最近打开的文件
 *         <p>
 *         ALT + HOME: 打开导航bar;
 *         <p>
 *         CTRL + N: 查找class
 *         <p>
 *         CTRL + SHIFT + N: 查找文件
 *         <p>
 *         CTRL + Alt + Shift + N:在整个工程中查找成员
 *         <p>
 *         ALT + F1: 选择当前打开文件的窗口（project explorer or tool bar）
 *         <p>
 *         ALT + 1 : open /hide the Project tool window
 *         <p>
 *         ALT + 9: open/ hide the change tool window:
 *         <p>
 *         ALT + F12: open/hide the terminal tool window;
 *         <p>
 *         CTRL + SHIFT + T: 创建单元测试
 *         <p>
 *         CTRL + H: 了解类的继承关系
 *         <p>
 *         CTRL + ALT + U:可视化类的继承关系
 *         <p>
 *         CTRL + ALT + H: 查找方法被调用的地方
 *         <p>
 *         Alt + Ctrl + F7：查找Symbol被调用的地方
 *         <p>
 *         F11: bookmarks;
 *         <p>
 *         Shift + F11: see all bookmarks;
 *         <p>
 *         CTRL + ALT + O:移除未被使用的import项目；
 *         <p>
 *         CTRL + SHIFT + B: 打开方法返回值类型的定义；
 */
@Listeners({AssertionListener.class})

public class test {

    @Test
    public void f() {
        int arr[] = {11,22,43,21,56,10};
        int t ;
        for (int i = 0; i < arr.length-1; i++) {
            for (int k = i+1; k < arr.length; k++) {
                if (arr[i] > arr[k]){
                    t = arr[i];
                    arr[i] = arr[k];
                    arr[k] = t;
                }
            }
        }
        for (int j = 0; j < arr.length; j++) {
            //System.out.println(arr[j]);
        }

        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.DATE, -3);
        System.out.println(calendar.get(Calendar.DATE));
    }

    @Test
    public void ff(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(2,2);
        softAssert.assertAll();
    }
}
