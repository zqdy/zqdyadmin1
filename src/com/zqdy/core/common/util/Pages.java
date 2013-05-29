package com.zqdy.core.common.util;

import com.opensymphony.xwork2.util.ValueStack;

import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.components.Component;

/**
 * 分页逻辑Bean
 * 
 * @author tangs
 */
public class Pages extends Component {
	private Integer cpage;

	private Integer total;

	private String url;

	private String formId;
	
	 private String urlType;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Integer getCpage() {
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Pages(ValueStack arg0) {
		super(arg0);
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			StringBuilder str = new StringBuilder();
			boolean isValid = true;

			// 从ValueStack中取出数值
			 if (isValid) {
			// if (total.startsWith("%{") && total.endsWith("}")) {
			// total = total.substring(2, total.length() -1);
			 total = (Integer)this.getStack().findValue(String.valueOf(total));
			 isValid = total == null ? false : true;
			} else {
			isValid = false;}
			// }
			// }
			// if (isValid) {
			// if (cpage.startsWith("%{") && cpage.endsWith("}")) {
			// cpage = cpage.substring(2, cpage.length() - 1);
			// cpage = (String)this.getStack().findValue(cpage);
			// isValid = cpage == null ? false : true;
			// } else {
			// isValid = false;
			// }
			// }
			// if (isValid) {
			// if (url.startsWith("%{") && url.endsWith("}")) {
			// url = url.substring(2, url.length() - 1);
			// url = (String)this.getStack().findValue(url);
			// isValid = url == null ? false : true;
			// } else {
			// isValid = false;
			// }
			// }

			if (isValid) {
				Integer cpageInt = Integer.valueOf(cpage);
				if (0==total.intValue()) {
					str.append("没有可显示的数据!");
				} else {
					str.append("&nbsp;共"+total+"页&nbsp; 第"+cpage+"	页");
					// 当前页与总页数相等
					if (cpage.equals(total)) {
						// 如果total = 1，则无需分页，显示“[第1页] [共1页]”
						if (1==total.intValue()) {

							str.append("&nbsp;[第 " + cpage + " 页]");
							str.append(" &nbsp;[共 " + total + " 页]");
						} else {
							// 到达最后一页,显示“[首页] [上一页] [末页]”
							str
									.append("&nbsp;<a href=\"javascript:goPage(1)\">[首页]</a> ");
							str.append("&nbsp;<a href=javascript:goPage("
									+ (cpageInt - 1) + ")>[上一页]</a>");
							str.append("&nbsp;[下一页]");
							str.append("&nbsp;[末页]");

						}
					} else {
						// 当前页与总页数不相同
						if (cpage.intValue()==1) {
							// 第一页，显示“[首页] [下一页] [末页]”
							str.append("&nbsp;[首页]");
							str.append("&nbsp;[上一页]");
							str.append("&nbsp;<a href=javascript:goPage("
									+ (cpageInt + 1) + ")>[下一页]</a>");
							str.append("&nbsp;<a href=javascript:goPage(" + total
									+ ")>[末页]</a>\n");
						} else {
							// 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
							str
									.append("&nbsp;<a href=\"javascript:goPage(1);\">[首页]</a> ");
							str.append("&nbsp;<a href=javascript:goPage("
									+ (cpageInt - 1) + ")>[上一页]</a>");
							str.append("&nbsp;<a href=javascript:goPage("
									+ (cpageInt + 1) + ")>[下一页]</a>");
							str.append("&nbsp;<a href=javascript:goPage(" + total
									+ ")>[末页]</a>\n");
						}
					}
					str
							.append("<input type=\"hidden\" name=\"cpage\" id=\"_cpage\" value="+cpage+">");
					str
							.append("<input type=\"hidden\" name=\"total\" id=\"_total\" value=\""
									+ total + "\">");
					str
							.append("<script>\n")
							.append(" \n function goPage(thePage){\n")
							.append(
									"    var form = document.getElementById(\""
											+ formId + "\");")
							.append(
									"    document.getElementById(\"_cpage\").value= thePage;\n")
							.append("    form.submit();\n").append("  }\n");
					str.append("</script>\n");

				}
			}
			writer.write(str.toString());

		} catch (Exception ex) {
			Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
}
