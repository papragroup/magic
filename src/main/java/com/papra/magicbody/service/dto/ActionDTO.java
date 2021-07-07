package com.papra.magicbody.service.dto;

import com.papra.magicbody.domain.SubCategory;
import java.io.Serializable;
import java.util.Objects;
import javax.mail.Session;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.papra.magicbody.domain.Action} entity.
 */
public class ActionDTO implements Serializable {

    private Long id;

    private String title;

    private Long bookMarkId;

    private String photoUrl;

    @Lob
    private byte[] photo;

    private String photoContentType;

    private String code;

    @Lob
    private byte[] video;

    private String videoContentType;
    private String videoUrl;

    private String masterDescription;

    private SubCategoryDTO subCategory;

    private PracticeSessionDTO session;

    public Long getBookMarkId() {
        return bookMarkId;
    }

    public ActionDTO setBookMarkId(Long bookMarkId) {
        this.bookMarkId = bookMarkId;
        return this;
    }

    public PracticeSessionDTO getSession() {
        return session;
    }

    public ActionDTO setSession(PracticeSessionDTO session) {
        this.session = session;
        return this;
    }

    public SubCategoryDTO getSubCategory() {
        return subCategory;
    }

    public ActionDTO setSubCategory(SubCategoryDTO subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMasterDescription() {
        return masterDescription;
    }

    public void setMasterDescription(String masterDescription) {
        this.masterDescription = masterDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionDTO)) {
            return false;
        }

        ActionDTO actionDTO = (ActionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, actionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActionDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", code='" + getCode() + "'" +
            ", video='" + getVideo() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", masterDescription='" + getMasterDescription() + "'" +
            "}";
    }
}
