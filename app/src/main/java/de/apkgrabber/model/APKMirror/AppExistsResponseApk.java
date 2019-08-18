package de.apkgrabber.model.APKMirror;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppExistsResponseApk {

	@SerializedName("version_code")
	@Expose
	private String versionCode;

	@SerializedName("link")
	@Expose
	private String link;

	@SerializedName("publish_date")
	@Expose
	private String publishDate;

	@SerializedName("arches")
	@Expose
	private List<String> arches = null;

	@SerializedName("dpis")
	@Expose
	private List<String> dpis = null;

	@SerializedName("minapi")
	@Expose
	private String minapi;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("capabilities")
	@Expose
	private List<String> capabilities = null;

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public List<String> getArches() {
		return arches;
	}

	public void setArches(List<String> arches) {
		this.arches = arches;
	}

	public List<String> getDpis() {
		return dpis;
	}

	public void setDpis(List<String> dpis) {
		this.dpis = dpis;
	}

	public String getMinapi() {
		return minapi;
	}

	public void setMinapi(String minapi) {
		this.minapi = minapi;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}

}


