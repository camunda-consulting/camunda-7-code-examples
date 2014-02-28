package org.camunda.bpm.example.acm.cmis;

import java.io.Serializable;

public class DocumentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    // could also add date / ...

    /**
     * @param name
     * @param url
     */
    public DocumentDto(final String name, final String url) {
        super();
        this.name = name;
        this.url = url;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

}
