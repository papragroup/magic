package com.papra.magicbody.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A SubCategory.
 */
@Entity
@Table(name = "sub_category")
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "photo_url")
    private String photoUrl;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "voice_url")
    private String voiceUrl;

    @Lob
    @Column(name = "voice_file")
    private byte[] voiceFile;

    @Column(name = "voice_file_content_type")
    private String voiceFileContentType;

    @Column(name = "master_description")
    private String masterDescription;

    @Column(name = "master_advice")
    private String masterAdvice;

    @ManyToOne
    @JsonIgnoreProperties(value = { "subcategories", "actions" }, allowSetters = true)
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubCategory id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public SubCategory title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public SubCategory photoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public SubCategory photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return this.photoContentType;
    }

    public SubCategory photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getVoiceUrl() {
        return this.voiceUrl;
    }

    public SubCategory voiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public byte[] getVoiceFile() {
        return this.voiceFile;
    }

    public SubCategory voiceFile(byte[] voiceFile) {
        this.voiceFile = voiceFile;
        return this;
    }

    public void setVoiceFile(byte[] voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getVoiceFileContentType() {
        return this.voiceFileContentType;
    }

    public SubCategory voiceFileContentType(String voiceFileContentType) {
        this.voiceFileContentType = voiceFileContentType;
        return this;
    }

    public void setVoiceFileContentType(String voiceFileContentType) {
        this.voiceFileContentType = voiceFileContentType;
    }

    public String getMasterDescription() {
        return this.masterDescription;
    }

    public SubCategory masterDescription(String masterDescription) {
        this.masterDescription = masterDescription;
        return this;
    }

    public void setMasterDescription(String masterDescription) {
        this.masterDescription = masterDescription;
    }

    public String getMasterAdvice() {
        return this.masterAdvice;
    }

    public SubCategory masterAdvice(String masterAdvice) {
        this.masterAdvice = masterAdvice;
        return this;
    }

    public void setMasterAdvice(String masterAdvice) {
        this.masterAdvice = masterAdvice;
    }

    public Category getCategory() {
        return this.category;
    }

    public SubCategory category(Category category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubCategory)) {
            return false;
        }
        return id != null && id.equals(((SubCategory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubCategory{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", voiceUrl='" + getVoiceUrl() + "'" +
            ", voiceFile='" + getVoiceFile() + "'" +
            ", voiceFileContentType='" + getVoiceFileContentType() + "'" +
            ", masterDescription='" + getMasterDescription() + "'" +
            ", masterAdvice='" + getMasterAdvice() + "'" +
            "}";
    }
}
