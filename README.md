# StarAds

1. 包命名

域名反写+项目名称+模块名称，全部单词用小写字母。
如：
com.starwinwin.ads.model

2. 类和接口命名

使用大驼峰规则，用名词或名词词组命名，每个单词的首字母大写。
以下为几种常用类的命名：

activity类，命名以Activity为后缀，如：LoginActivity
fragment类，命名以Fragment为后缀，如：ShareDialogFragment
service类，命名以Service为后缀，如：DownloadService
adapter类，命名以Adapter为后缀，如：CouponListAdapter
工具类，命名以Util为后缀，如：EncryptUtil
模型类，命名以Bean为后缀，如：CouponBean
接口实现类，命名以Impl为后缀，如：ApiImpl
3. 方法命名

使用小驼峰规则，用动词命名，第一个单词的首字母小写，其他单词的首字母大写。
以下为几种常用方法的命名：

初始化方法，命名以init开头，例：initView
按钮点击方法，命名以to开头，例：toLogin
设置方法，命名以set开头，例：setData
具有返回值的获取方法，命名以get开头，例：getData
通过异步加载数据的方法，命名以load开头，例：loadData
布尔型的判断方法，命名以is或has，或具有逻辑意义的单词如equals，例：isEmpty
4. 控件缩写

控件	            缩写
TextView	    tv
EditText	    edt
Button	        btn
ImageButton	    ibtn
ImageView	    img
ListView	    lv
RadioGroup	    rgroup
RadioButton	    rbtn
ProgressBar	    progress
SeekBar	        seek
CheckBox	    chk
Spinner	        spinner
TableLayout	    table
TableRow	    row
LinearLayout	llayout
RelativeLayout	rlayout
ScrollView	    sv
SearchView	    search

5. 常量命名

全部为大写单词，单词之间用下划线分开。

public final static int PAGE_SIZE = 20;
6. 变量命名

{范围描述+}意义描述+类型描述的组合，用驼峰式，首字母小写。

private TextView headerTitleTxt; // 标题栏的标题
private Button loginBtn; // 登录按钮
private CouponBO couponBO; // 券实例
7. 控件id命名

控件缩写_{范围_}意义，范围可选，只在有明确定义的范围内才需要加上。

<!-- 这是标题栏的标题 -->
<TextView
    android:id="@+id/tv_header_title"
    ... />

<!-- 这是登录按钮 -->
<Button
    android:id="@+id/btn_login"
    ... />
8. layout命名

组件类型_{范围_}功能，范围可选，只在有明确定义的范围内才需要加上。
以下为几种常用的组件类型命名：

activity_{范围_}功能，为Activity的命名格式
fragment_{范围_}功能，为Fragment的命名格式
dialog_{范围_}功能，为Dialog的命名格式
item_list_{范围_}功能，为ListView的item命名格式
item_grid_{范围_}功能，为GridView的item命名格式
header_list_{范围_}功能，为ListView的HeaderView命名格式
footer_list_{范围_}功能，为ListView的FooterView命名格式
9. strings的命名

类型_{范围_}功能，范围可选。
以下为几种常用的命名：

页面标题，命名格式为：title_页面
按钮文字，命名格式为：btn_按钮事件
标签文字，命名格式为：label_标签文字
选项卡文字，命名格式为：tab_选项卡文字
消息框文字，命名格式为：toast_消息
编辑框的提示文字，命名格式为：hint_提示信息
图片的描述文字，命名格式为：desc_图片文字
对话框的文字，命名格式为：dialog_文字
menu的item文字，命名格式为：action_文字
10. colors的命名

前缀{_控件}{_范围}{_后缀}，控件、范围、后缀可选，但控件和范围至少要有一个。

背景颜色，添加bg前缀
文本颜色，添加text前缀
分割线颜色，添加div前缀
区分状态时，默认状态的颜色，添加normal后缀
区分状态时，按下时的颜色，添加pressed后缀
区分状态时，选中时的颜色，添加selected后缀
区分状态时，不可用时的颜色，添加disable后缀
11. drawable的命名

前缀{_控件}{_范围}{_后缀}，控件、范围、后缀可选，但控件和范围至少要有一个。

图标类，添加ic前缀
背景类，添加bg前缀
分隔类，添加div前缀
默认类，添加def前缀
区分状态时，默认状态，添加normal后缀
区分状态时，按下时的状态，添加pressed后缀
区分状态时，选中时的状态，添加selected后缀
区分状态时，不可用时的状态，添加disable后缀
多种状态的，添加selector后缀（一般为ListView的selector或按钮的selector）
12. 动画文件命名

动画类型_动画方向。

fade_in，淡入
fade_out，淡出
push_down_in，从下方推入
push_down_out，从下方推出
slide_in_from_top，从头部滑动进入
zoom_enter，变形进入
shrink_to_middle，中间缩小
