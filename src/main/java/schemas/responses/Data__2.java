
package schemas.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import schemas.objects.RecentOrderOrder;

@Generated("jsonschema2pojo")
public class Data__2 implements Serializable
{

    @SerializedName("currentPage")
    @Expose
    private Long currentPage;
    @SerializedName("pageSize")
    @Expose
    private Long pageSize;
    @SerializedName("totalNum")
    @Expose
    private Long totalNum;
    @SerializedName("totalPage")
    @Expose
    private Long totalPage;
    @SerializedName("items")
    @Expose
    private List<RecentOrderOrder> items = new ArrayList<RecentOrderOrder>();
    private final static long serialVersionUID = -7835548500615333031L;

    public Long getCurrentPage() {
        return currentPage;
    }

    public Data__2 withCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Data__2 withPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public Data__2 withTotalNum(Long totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public Data__2 withTotalPage(Long totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public List<RecentOrderOrder> getItems() {
        return items;
    }

    public Data__2 withItems(List<RecentOrderOrder> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentPage");
        sb.append('=');
        sb.append(((this.currentPage == null)?"<null>":this.currentPage));
        sb.append(',');
        sb.append("pageSize");
        sb.append('=');
        sb.append(((this.pageSize == null)?"<null>":this.pageSize));
        sb.append(',');
        sb.append("totalNum");
        sb.append('=');
        sb.append(((this.totalNum == null)?"<null>":this.totalNum));
        sb.append(',');
        sb.append("totalPage");
        sb.append('=');
        sb.append(((this.totalPage == null)?"<null>":this.totalPage));
        sb.append(',');
        sb.append("items");
        sb.append('=');
        sb.append(((this.items == null)?"<null>":this.items));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.pageSize == null)? 0 :this.pageSize.hashCode()));
        result = ((result* 31)+((this.currentPage == null)? 0 :this.currentPage.hashCode()));
        result = ((result* 31)+((this.totalNum == null)? 0 :this.totalNum.hashCode()));
        result = ((result* 31)+((this.items == null)? 0 :this.items.hashCode()));
        result = ((result* 31)+((this.totalPage == null)? 0 :this.totalPage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data__2) == false) {
            return false;
        }
        Data__2 rhs = ((Data__2) other);
        return ((((((this.pageSize == rhs.pageSize)||((this.pageSize!= null)&&this.pageSize.equals(rhs.pageSize)))&&((this.currentPage == rhs.currentPage)||((this.currentPage!= null)&&this.currentPage.equals(rhs.currentPage))))&&((this.totalNum == rhs.totalNum)||((this.totalNum!= null)&&this.totalNum.equals(rhs.totalNum))))&&((this.items == rhs.items)||((this.items!= null)&&this.items.equals(rhs.items))))&&((this.totalPage == rhs.totalPage)||((this.totalPage!= null)&&this.totalPage.equals(rhs.totalPage))));
    }

}
