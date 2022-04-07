package com.hubilo.lite

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Session")
data class SessionResponse(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @field:SerializedName("selectableDates")
    val selectableDates: List<String>? = null,

    @field:SerializedName("user_host_id")
    val userHostId: String? = null,

    @field:SerializedName("isSendLiveAgenda")
    val isSendLiveAgenda: String? = null,

    @field:SerializedName("isStudioHost")
    val isStudioHost: String? = null,

    @field:SerializedName("liveAgenda")
    val liveAgenda: List<AgendaItemItem?>? = null,

    @field:SerializedName("selectedDate")
    val selectedDate: String? = null,

    @field:SerializedName("agenda")
    var agenda: List<AgendaItemItem?>? = null,

    @field:SerializedName("user_speaker_id")
    val userSpeakerId: String? = null,

    @field:SerializedName("isZoomSdk")
    val isZoomSdk: String? = null,

    @field:SerializedName("results")
    val results: List<ResultsItem?>? = null,

)

data class ResultsItem(

    @field:SerializedName("startTimeMilli")
    val startTimeMilli: String? = null,

    @field:SerializedName("speakers")
    val speakers: List<UpcomingSessionSpeakersItem?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("endTimeMilli")
    val endTimeMilli: String? = null
)

data class SpeakersItem (

    @field:SerializedName("profile_img")
    val profileImg: String? = null,

    @field:SerializedName("short_description")
    val shortDescription: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("organiser_id")
    val organiserId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("agenda_id")
    val agendaId: String? = null,

    @field:SerializedName("isBookmark")
    val isBookmark: String? = null,

    @field:SerializedName("dominantColor")
    val dominantColor: Any? = null,

    @field:SerializedName("profileImg")
    val profileImgMySch: String? = null,
)


data class UpcomingSessionSpeakersItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("isBookmark")
    val isBookmark: String? = null,

    @field:SerializedName("dominantColor")
    val dominantColor: Any? = null,

    @field:SerializedName("organiserId")
    val organiserIdUpcoming: Int? = null,

    @field:SerializedName("shortDescription")
    val shortDescriptionUpcoming : String? = null,

    @field:SerializedName("profileImg")
    val profileImgUpcoming: String? = null,

    @field:SerializedName("agendaId")
    val agendaIdUpcoming: Int? = null,

    )

@Entity(tableName = "AgendaInfo")
data class  AgendaInfo  (

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("player_type_id")
    val playerTypeId: String? = null,

    @field:SerializedName("is_self_hosted")
    val isSelfHosted: String? = null,

    @field:SerializedName("isInMySchedule")
    var isInMySchedule: String? = null,

    @field:SerializedName("stream_type_id")
    val streamTypeId: Int? = null,

    @field:SerializedName("meeting_id")
    val meetingId: String? = null,

    @field:SerializedName("is_let_unregister")
    val isLetUnregister: String? = null,

    @field:SerializedName("player_meta_type_id")
    val playerMetaTypeId: String? = null,

    @field:SerializedName("is_stream")
    val isStream: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("is_registration_status_open")
    val isRegistrationStatusOpen: Int? = null,

    @field:SerializedName("agenda_track_id")
    val agendaTrackId: String? = null,

    @field:SerializedName("is_attendee_registration")
    val isAttendeeRegistration: String? = null,

    @field:SerializedName("pre_recorded_is_replay_video")
    val preRecordedIsReplayVideo: String? = null,

    @field:SerializedName("at_position")
    val atPosition: String? = null,

    @field:SerializedName("end_time_milli")
    val endTimeMilli: String? = null,

    @field:SerializedName("registration_start_time_milli")
    val registrationStartTimeMilli: String? = "0",

    @field:SerializedName("start_time_milli")
    val startTimeMilli: String? = "",

    /*@field:SerializedName("webinar_id")
    val webinarId: Any? = null,*/

    @field:SerializedName("hosting_properties")
    val hostingProperties: String? = null,

    @field:SerializedName("webinar_host_id")
    val webinarHostId: Int? = null,

    @field:SerializedName("track_name")
    val trackName: String? = null,

    @field:SerializedName("registration_limit")
    val registrationLimit: String? = null,

    @field:SerializedName("stream_link")
    val streamLink: String? = null,

    @field:SerializedName("is_waitlist_after_limit")
    val isWaitlistAfterLimit: String? = null,

    @field:SerializedName("userRating")
    val userRating: String? = null,

    @field:SerializedName("tags")
    val tags: String? = null,

    @field:SerializedName("registration_end_time_milli")
    val registrationEndTimeMilli: String? = "0",

    @field:SerializedName("is_waitlist_registration")
    val isWaitlistRegistration: String? = null,

    /*@field:SerializedName("rtmp_source_id")
    val rtmpSourceId: Any? = null,*/

    @field:SerializedName("is_featured")
    val isFeatured: String? = null,

    @field:SerializedName("stream_recording_link")
    val streamRecordingLink: String? = "",

    @field:SerializedName("agendaImage")
    val agendaImage: String? = null,

    @field:SerializedName("fileName")
    val fileName: String? = null,

    @field:SerializedName("is_restricted")
    val isRestricted: Int? = null,

    @field:SerializedName("isModerateQna")
    val isModerateQna: Int? = null,

    @field:SerializedName("custom_iframe_btn_label")
    val customIframeBtnLabel: String? = null,

    @field:SerializedName("multipleFile")
    val multipleFile: List<MultipleFileItem?>? = null,

    @field:SerializedName("preRecordedMuxUploadStatus")
    val preRecordedMuxUploadStatus: Int? = null,

    @field:SerializedName("isRating")
    val isRating: Int? = null,

    /*@field:SerializedName("streamTypeId")
    val streamTypeId: Int? = null,*/

    @field:SerializedName("isLanguageInterpretation")
    val isLanguageInterpretation: Int? = null,

    /*@field:SerializedName("symblTimestamp")
    val symblTimestamp: Any? = null,*/

    @field:SerializedName("interpretationServiceTokenLink")
    val interpretationServiceTokenLink: String? = null,

    /*@field:SerializedName("symblConversationId")
    val symblConversationId: Any? = null,*/

    /*@field:SerializedName("isRestricted")
    val isRestricted: Int? = null,*/

    /*@field:SerializedName("interpretationServiceMetaId")
    val interpretationServiceMetaId: Any? = null,*/

    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "customIframeInfo")
    @field:SerializedName("customIframeInfo")
    val customIframeInfo: CustomIframeInfo? = null,

    /*@field:SerializedName("symblConnectionId")
    val symblConnectionId: Any? = null,*/

    @field:SerializedName("is_custom_iframe")
    val isCustomIframe: Int? = null,

    /*@field:SerializedName("restricted_to_groups")
    val restrictedToGroups: Any? = null,*/

    @field:SerializedName("videoEmbedIFrame")
    val videoEmbedIFrame: String? = null,

    @field:SerializedName("custom_iframe_code")
    val customIframeCode: String? = null,

    @field:SerializedName("appVimeoId")
    val appVimeoId: String? = null,

    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "interpretationInfo")
    @field:SerializedName("interpretationInfo")
    val interpretationInfo: InterpretationInfo? = null,

    /*@field:SerializedName("symblSubscriptionUrl")
    val symblSubscriptionUrl: Any? = null,*/

    @field:SerializedName("realFileName")
    val realFileName: String? = null,

    @field:SerializedName("preRecordedLiveM3u8Link")
    val preRecordedLiveM3u8Link: String? = null,


    @field:SerializedName("webinar_id")
    val webinarId: String? = null,

    @field:SerializedName("isChatTagging")
    val isChatTagging: Boolean? = false

    /*@field:SerializedName("playerMetaData")
    val playerMetaData: Any? = null,*/

    /*@field:SerializedName("webinarHostId")
    val webinarHostId: Any? = null,

    @field:SerializedName("rtmpSourceId")
    val rtmpSourceId: Any? = null,*/

    /*@field:SerializedName("restrictedToGroups")
    val restrictedToGroups: Any? = null*/
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(

    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeValue(id)
        dest.writeString(playerTypeId)
        dest.writeString(isSelfHosted)
        dest.writeString(isInMySchedule)
        dest.writeString(meetingId)
        dest.writeString(isLetUnregister)
        dest.writeString(playerMetaTypeId)
        dest.writeString(isStream)
        dest.writeString(description)
        dest.writeValue(isRegistrationStatusOpen)
        dest.writeString(agendaTrackId)
        dest.writeString(isAttendeeRegistration)
        dest.writeString(preRecordedIsReplayVideo)
        dest.writeString(atPosition)
        dest.writeString(endTimeMilli)
        dest.writeString(registrationStartTimeMilli)
        dest.writeString(startTimeMilli)
        dest.writeString(hostingProperties)
        dest.writeString(trackName)
        dest.writeString(registrationLimit)
        dest.writeString(streamLink)
        dest.writeString(isWaitlistAfterLimit)
        dest.writeString(userRating)
        dest.writeString(tags)
        dest.writeString(registrationEndTimeMilli)
        dest.writeString(isWaitlistRegistration)
        dest.writeString(isFeatured)
        dest.writeString(streamRecordingLink)
        dest.writeString(agendaImage)
        dest.writeString(fileName)
        dest.writeValue(isRestricted)
        dest.writeValue(isModerateQna)
        dest.writeString(customIframeBtnLabel)
        dest.writeValue(preRecordedMuxUploadStatus)
        dest.writeValue(isRating)
        dest.writeValue(streamTypeId)
        dest.writeValue(isLanguageInterpretation)
        dest.writeString(interpretationServiceTokenLink)
        dest.writeValue(isCustomIframe)
        dest.writeString(videoEmbedIFrame)
        dest.writeString(customIframeCode)
        dest.writeString(appVimeoId)
        dest.writeString(realFileName)
        dest.writeString(preRecordedLiveM3u8Link)
        dest.writeByte((if (isChatTagging == true) 1 else 0).toByte())
    }

    companion object CREATOR : Parcelable.Creator<AgendaInfo> {
        override fun createFromParcel(parcel: Parcel): AgendaInfo {
            return AgendaInfo(parcel)
        }

        override fun newArray(size: Int): Array<AgendaInfo?> {
            return arrayOfNulls(size)
        }
    }
}

@Entity(tableName = "AgendaItemItem")
data class AgendaItemItem(

    @field:SerializedName("registration_limit")
    val registrationLimit: String? = null,

    @field:SerializedName("is_registred")
    var isRegistred: String? = "NO",

    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "agendaInfo")
    @field:SerializedName("agendaInfo")
    val agendaInfo: AgendaInfo? = null,

    @field:SerializedName("registration_status")
    var registrationStatus: String? = null,

    /*@field:SerializedName("sponsors")
    val sponsors: List<Any?>? = null,*/

    @field:SerializedName("speakers")
    val speakers: ArrayList<SpeakersItem?>? = null,

    @field:SerializedName("isRegistrationUnlimited")
    val isRegistrationUnlimited: String? = null,

    @field:SerializedName("exhibitors")
    val exhibitors: ArrayList<ExhibitorsItem?>? = null,

    @field:SerializedName("title")
    val title: String? = null,


    @PrimaryKey
    @field:SerializedName("agenda_id")
    val agendaId: Int? = null
) :  Parcelable {
    constructor(parcel: Parcel) : this(
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AgendaItemItem> {
        override fun createFromParcel(parcel: Parcel): AgendaItemItem {
            return AgendaItemItem(parcel)
        }

        override fun newArray(size: Int): Array<AgendaItemItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class LiveAgendaItem(

    @field:SerializedName("registration_limit")
    val registrationLimit: String? = null,

    @field:SerializedName("is_registred")
    val isRegistred: String? = null,

    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "agendaInfo")
    @field:SerializedName("agendaInfo")
    val agendaInfo: AgendaInfo? = null,

    @field:SerializedName("registration_status")
    val registrationStatus: String? = null,

    /*@field:SerializedName("sponsors")
    val sponsors: List<Any?>? = null,*/

    @field:SerializedName("speakers")
    val speakers: List<SpeakersItem?>? = null,

    @field:SerializedName("isRegistrationUnlimited")
    val isRegistrationUnlimited: String? = null,

    /*@field:SerializedName("exhibitors")
    val exhibitors: List<Any?>? = null,*/

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("agenda_id")
    val agendaId: String? = null,

    @field:SerializedName("isBookmark")
    val isBookmark: String? = null
)

data class ExhibitorsItem(

    @field:SerializedName("profile_img")
    val profileImg: String? = null,

    @field:SerializedName("exhibitor_id")
    val exhibitorId: Int? = null,

    @field:SerializedName("organiser_id")
    val organiserId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("ase_id")
    val aseId: Int? = null,

    @field:SerializedName("agenda_id")
    val agendaId: Int? = null,

    @field:SerializedName("isBookmark")
    val isBookmark: Boolean? = null
)

data class MultipleFileItem(

    @field:SerializedName("filename")
    val filename: String? = null,

    @field:SerializedName("real_filename")
    val realFilename: String? = null,

    @field:SerializedName("isInBriefcase")
    val isInBriefcase: String? = null,

    @field:SerializedName("format")
    val format: String? = null
)

data class CustomIframeInfo(

    @field:SerializedName("customIframeBtnLabel")
    val customIframeBtnLabel: String? = null,

    @field:SerializedName("isCustomIframe")
    val isCustomIframe: Int? = null,

    @field:SerializedName("customIframeCode")
    val customIframeCode: String? = null
)

data class UsersItem(

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("profilePictures")
    val profilePictures: ProfilePictures? = null,

    @field:SerializedName("organiser_id")
    val organiserId: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("designation")
    val designation: String? = null,

    @field:SerializedName("organisation_name")
    val organisationName: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)

data class ProfilePictures(

    @field:SerializedName("thumb")
    val thumb: String? = null,

    @field:SerializedName("orignal")
    val orignal: String? = null
)

data class InterpretationInfo(

    @field:SerializedName("isLanguageInterpretation")
    val isLanguageInterpretation: Int? = null,

    @field:SerializedName("interpretationServiceTokenLink")
    val interpretationServiceTokenLink: String? = null,

    /*@field:SerializedName("interpretationServiceMetaId")
    val interpretationServiceMetaId: Any? = null*/
)
