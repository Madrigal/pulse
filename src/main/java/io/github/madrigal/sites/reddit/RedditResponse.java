
package io.github.madrigal.sites.reddit;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.github.madrigal.sites.reddit.generated.Data;

@Generated("org.jsonschema2pojo")
public class RedditResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
