package com.example.samridhi.miwok;

/**
 * Created by Samridhi on 11-01-2018.
 */

public class word {

private String mDefaultTranslation;
private String mMiwokTranslation;
private int ImageResourceId = NO_IMAGE_PROVIDED;
private static final int NO_IMAGE_PROVIDED = -1;
private int mAudioResourceId;

    public boolean hasImage() {
                return ImageResourceId != NO_IMAGE_PROVIDED;
            }

public word (String mDefaultTranslation,String mMiwokTranslation,int AudioResourceId){
    this.mDefaultTranslation = mDefaultTranslation ;
    this.mMiwokTranslation   = mMiwokTranslation ;
    this.mAudioResourceId = AudioResourceId ;
}
public word(String mDefaultTranslation,String mMiwokTranslation,int ImageResourceId,int AudioResourceId){
    this.mDefaultTranslation = mDefaultTranslation;
    this.mMiwokTranslation = mMiwokTranslation;
    this.ImageResourceId = ImageResourceId;
    this.mAudioResourceId = AudioResourceId;
}
public String  getmDefaultTranslation()
{
    return mDefaultTranslation;
}
public String getmMiwokTranslation(){
    return mMiwokTranslation;
}

public int ImageResourceId(){
    return ImageResourceId;
}

    public int getAudioResourceId() {
        return mAudioResourceId;
    }}