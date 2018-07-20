package com.fooee.winqing.management.dao.vdo.book;

import com.fooee.commons.compontent.query.QueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * BookInf查询条件
 * @author pangzhenhua
 * @version 2016-11-29 23:08:52
 */
public class BookInfoQc extends QueryCondition {
	/**
	 *<p>作者名称</p>
	 */
	private String authorName;
	/**
	 *<p>装帧名称</p>
	 */
	private String bindingName;
	/**
	 *<p>图书名称</p>
	 */
	@NotBlank(message = "{book.bookName.null}")
	private String bookName;
	/**
	 *<p>图书名称列表
	 */
	private List<String> bookNames;
	/**
	 *<p>图书价格</p>
	 */
	private BigDecimal bookPrice;
	/**
	 *<p>创建时间</p>
	 */
	private Date createTime;
	/**
	 *<p></p>
	 */
	private Integer id;
	/**
	 *<p>ISBN</p>
	 */
	private String isbn;
	/**
	 * isbn列表
     */
	private List<String> isbns;
	/**
	 *<p>页数</p>
	 */
	private Integer pageNumber;
	/**
	 *<p>图片地址</p>
	 */
	private String pictureAddress;
	/**
	 *<p>出版社名称</p>
	 */
	private String pressName;
	/**
	 *<p>出版日期</p>
	 */
	private Date publishDate;
	/**
	 *<p>评分数量</p>
	 */
	private BigDecimal scoreNumber;
	/**
	 * 评论数量
	 */
	private Integer commentNumber;
	/**
	 *<p>文青ID</p>
	 */
	private Integer wqId;
	/**
	 *<p>译者名称</p>
	 */
	private String translatorName;
	/**
	 *<p>副标题</p>
	 */
	private String subTitle;

	/**
	 * 图片信息
     */
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * 评论数量
	 * @return
	 */
	public Integer getCommentNumber() {
		return commentNumber;
	}

	/**
	 * 评论数量
	 * @param commentNumber
	 */
	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}

	/**
	 * 作者名字
	 * @return
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * 作者名字
	 * @param authorName
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * 装帧名称
	 * @return
	 */
	public String getBindingName() {
		return bindingName;
	}

	/**
	 * 装帧名称
	 * @param bindingName
	 */
	public void setBindingName(String bindingName) {
		this.bindingName = bindingName;
	}

	/**
	 * 图书名称
	 * @return
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * 图书名称
	 * @param bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<String> getBookNames() {
		return bookNames;
	}

	public void setBookNames(String bookNames) {
		if(null != bookNames && !"".equals(bookNames)) {
			this.bookNames = Arrays.asList(bookNames.split(","));
		}
	}

	public List<String> getIsbns() {
		return isbns;
	}

	public void setIsbns(String isbns) {
		if(isbns != null && !"".equals(isbns)){
			this.isbns = Arrays.asList(isbns.split(","));
		}
	}

	/**
	 * 图书价格
	 * @return
	 */
	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	/**
	 * 图书价格
	 * @param bookPrice
	 */
	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Integer id){
		this.id=id;
	}

	/**
	 *
	 * @return
	 */
	public Integer getId(){
		return this.id;
	}
	/**
	 * ISBN
	 * @param isbn
	 */
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}

	/**
	 * ISBN
	 * @return
	 */
	public String getIsbn(){
		return this.isbn;
	}

	/**
	 * 页数
	 * @return
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * 页数
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 图片地址
	 * @return
	 */
	public String getPictureAddress() {
		return pictureAddress;
	}

	/**
	 * 图片地址
	 * @param pictureAddress
	 */
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}

	/**
	 * 出版社名称
	 * @param pressName
	 */
	public void setPressName(String pressName){
		this.pressName=pressName;
	}

	/**
	 * 出版社名称
	 * @return
	 */
	public String getPressName(){
		return this.pressName;
	}
	/**
	 * 出版日期
	 * @param publishDate
	 */
	public void setPublishDate(Date publishDate){
		this.publishDate=publishDate;
	}

	/**
	 * 出版日期
	 * @return
	 */
	public Date getPublishDate(){
		return this.publishDate;
	}
	/**
	 * 评分数量
	 * @param scoreNumberm
	 */
	public void setScoreNumber(BigDecimal scoreNumberm){
		this.scoreNumber=scoreNumberm;
	}

	/**
	 * 评分数量
	 * @return
	 */
	public BigDecimal getScoreNumber(){
		return this.scoreNumber;
	}
	/**
	 * 文青ID
	 * @param wqId
	 */
	public void setWqId(Integer wqId){
		this.wqId=wqId;
	}

	/**
	 * 文青ID
	 * @return
	 */
	public Integer getWqId(){
		return this.wqId;
	}
	/**
	 * 译者名称
	 * @param translatorName
	 */
	public void setTranslatorName(String translatorName){
		this.translatorName=translatorName;
	}

	/**
	 * 译者名称
	 * @return
	 */
	public String getTranslatorName(){
		return this.translatorName;
	}
	/**
	 * 副标题
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle){
		this.subTitle=subTitle;
	}

	/**
	 * 副标题
	 * @return
	 */
	public String getSubTitle(){
		return this.subTitle;
	}
		
}
