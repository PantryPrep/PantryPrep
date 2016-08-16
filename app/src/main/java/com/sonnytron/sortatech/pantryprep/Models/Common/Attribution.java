package com.sonnytron.sortatech.pantryprep.Models.Common;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attribution {

    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("logo")
    @Expose
    private String logo;

    /**
     *
     * @return
     *     The html
     */
    public String getHtml() {
        return html;
    }

    /**
     *
     * @param html
     *     The html
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     *     The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     *     The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

}
