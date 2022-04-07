package com.hubilo.lite

import androidx.room.Embedded
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

data class SessionDetailResponse(

	@field:SerializedName("registrationStatus")
	val registrationStatus: String? = null,

	@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
	@Embedded(prefix = "agendaInfo")
	@field:SerializedName("agendaInfo")
	val agendaInfo: AgendaInfoDetail? = null,

	@field:SerializedName("isRegistrationUnlimited")
	val isRegistrationUnlimited: Boolean? = null,

	@field:SerializedName("isStudioHost")
	val isStudioHost: String? = null,

	@field:SerializedName("isModerateQna")
	val isModerateQna: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("agendaId")
	val agendaId: String? = null,

	@field:SerializedName("userSpeakerId")
	val userSpeakerId: String? = null,

	@field:SerializedName("symblToken")
	val symblToken: String? = null,

	@field:SerializedName("userHostId")
	val userHostId: String? = null,

	@field:SerializedName("feedId")
	val feedId: String? = null,

	@field:SerializedName("isRegistred")
	val isRegistred: String? = null,

	@field:SerializedName("speakers")
	val speakers: List<SpeakersItemDetail?>? = null,

	@field:SerializedName("exhibitors")
	val exhibitors: List<ExhibitorsItemDetail?>? = null,

	@field:SerializedName("attendeeCount")
	val attendeeCount: Int? = null,

	@field:SerializedName("mySchedule")
	val mySchedule: Int? = null,

	@field:SerializedName("registrationLimit")
	val registrationLimit: Int? = null,

	@field:SerializedName("broadcastStudioAgendaIds")
	val broadcastStudioAgendaIds: List<Any?>? = null,

	@field:SerializedName("engagements")
	val engagements: List<Engagement?>? = null,

	@field:SerializedName("survey")
	val survey: Survey? = null
)

data class Survey (
	@field:SerializedName("surveyId")
	var surveyId: String? = null,

	@field:SerializedName("isSurveyCompleted")
	var isSurveyCompleted:Boolean? = null,

	@field:SerializedName("surveyTrigger")
	var surveyTrigger: String? = null,

	@field:SerializedName("surveyStatus")
	var surveyStatus: String? = null,

	@field:SerializedName("title")
	var title: String? = null
)

data class UsersItemDetail(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
	@Embedded(prefix = "profilePictures")
	@field:SerializedName("profilePictures")
	val profilePictures: ProfilePicturesDetail? = null,

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

data class InterpretationInfoDetail(

	@field:SerializedName("isLanguageInterpretation")
	val isLanguageInterpretation: Int? = null,

	@field:SerializedName("interpretationServiceTokenLink")
	val interpretationServiceTokenLink: String? = null,

	/*@field:SerializedName("interpretationServiceMetaId")
	val interpretationServiceMetaId: Any? = null*/
)

data class AgendaInfoDetail(

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("isAttendeeRegistration")
	val isAttendeeRegistration: Int? = null,

	@field:SerializedName("is_restricted")
	val isRestricted: Int? = null,

	@field:SerializedName("hostingProperties")
	val hostingProperties: String? = null,

	@field:SerializedName("isModerateQna")
	val isModerateQna: Int? = null,

	@field:SerializedName("meetingId")
	val meetingId: String? = null,

	@field:SerializedName("trackName")
	val trackName: String? = null,

	@field:SerializedName("agendaImage")
	val agendaImage: String? = null,

	@field:SerializedName("custom_iframe_btn_label")
	val customIframeBtnLabel: String? = null,

	@field:SerializedName("multipleFile")
	val multipleFile: List<MultipleFileItemDetail?>? = null,

	@field:SerializedName("webinarId")
	val webinarId: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("preRecordedMuxUploadStatus")
	val preRecordedMuxUploadStatus: Int? = null,

	@field:SerializedName("isRating")
	val isRating: Int? = null,

	@field:SerializedName("registrationStartTimeMilli")
	val registrationStartTimeMilli: String? = null,

	@field:SerializedName("streamTypeId")
	val streamTypeId: Int? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("isSelfHosted")
	val isSelfHosted: Int? = null,

	@field:SerializedName("isLanguageInterpretation")
	val isLanguageInterpretation: Int? = null,

	@field:SerializedName("startTimeMilli")
	val startTimeMilli: String? = null,

	@field:SerializedName("registrationEndTimeMilli")
	val registrationEndTimeMilli: String? = null,

	/*@field:SerializedName("symblTimestamp")
	val symblTimestamp: Any? = null,*/

	@field:SerializedName("interpretationServiceTokenLink")
	val interpretationServiceTokenLink: String? = null,

	/*@field:SerializedName("symblConversationId")
	val symblConversationId: Any? = null,*/

	/*@field:SerializedName("interpretationServiceMetaId")
	val interpretationServiceMetaId: Any? = null,*/

	@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
	@Embedded(prefix = "customIframeInfo")
	@field:SerializedName("customIframeInfo")
	val customIframeInfo: CustomIframeInfoDetail? = null,

	@field:SerializedName("isInMySchedule")
	val isInMySchedule: Boolean? = null,

	@field:SerializedName("playerTypeId")
	val playerTypeId: Int? = null,

	@field:SerializedName("preRecordedIsReplayVideo")
	val preRecordedIsReplayVideo: Int? = null,

	@field:SerializedName("isStream")
	val isStream: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("streamRecordingLink")
	val streamRecordingLink: String? = null,

	@field:SerializedName("playerMetaTypeId")
	val playerMetaTypeId: Int? = null,

	/*@field:SerializedName("symblConnectionId")
	val symblConnectionId: Any? = null,*/

	@field:SerializedName("isLetUnregister")
	val isLetUnregister: Int? = null,

	@field:SerializedName("endTimeMilli")
	val endTimeMilli: String? = null,

	@field:SerializedName("is_custom_iframe")
	val isCustomIframe: Int? = null,

	@field:SerializedName("videoEmbedIFrame")
	val videoEmbedIFrame: String? = null,

	@field:SerializedName("custom_iframe_code")
	val customIframeCode: String? = null,

	@field:SerializedName("streamLink")
	val streamLink: String? = null,

	@field:SerializedName("appVimeoId")
	val appVimeoId: String? = null,

	@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
	@Embedded(prefix = "interpretationInfo")
	@field:SerializedName("interpretationInfo")
	val interpretationInfo: InterpretationInfoDetail? = null,

	/*@field:SerializedName("symblSubscriptionUrl")
	val symblSubscriptionUrl: Any? = null,*/

	@field:SerializedName("realFileName")
	val realFileName: String? = null,

	@field:SerializedName("isRegistrationStatusOpen")
	val isRegistrationStatusOpen: Int? = null,

	@field:SerializedName("userRating")
	val userRating: Int? = null,

	@field:SerializedName("preRecordedLiveM3u8Link")
	val preRecordedLiveM3u8Link: String? = null,

	@field:SerializedName("isWaitlistRegistration")
	val isWaitlistRegistration: Int? = null,

	/*@field:SerializedName("playerMetaData")
	val playerMetaData: Any? = null,*/

	@field:SerializedName("webinarHostId")
	val webinarHostId: Int? = null,

	@field:SerializedName("isWaitlistAfterLimit")
	val isWaitlistAfterLimit: String? = null,

	/*@field:SerializedName("rtmpSourceId")
	val rtmpSourceId: Any? = null,

	@field:SerializedName("restrictedToGroups")
	val restrictedToGroups: Any? = null*/

	@field:SerializedName("isChatTagging")
	val isChatTagging: Boolean? = false
)

data class ProfilePicturesDetail(

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("orignal")
	val orignal: String? = null
)

data class MultipleFileItemDetail(

	@field:SerializedName("filename")
	val filename: String? = null,

	@field:SerializedName("real_filename")
	val realFilename: String? = null,

	@field:SerializedName("isInBriefcase")
	var isInBriefcase: String? = null,

	@field:SerializedName("format")
	val format: String? = null
)

data class CustomIframeInfoDetail(

	@field:SerializedName("customIframeBtnLabel")
	val customIframeBtnLabel: String? = null,

	@field:SerializedName("isCustomIframe")
	val isCustomIframe: Int? = null,

	@field:SerializedName("customIframeCode")
	val customIframeCode: String? = null
)

data class SpeakersItemDetail(

	@field:SerializedName("longDescription")
	val longDescription: String? = null,

	@field:SerializedName("linkedinUrl")
	val linkedinUrl: String? = null,

	@field:SerializedName("numBookmark")
	val numBookmark: Int? = null,

	@field:SerializedName("twitterUrl")
	val twitterUrl: String? = null,

	@field:SerializedName("eventCount")
	val eventCount: Int? = null,

	@field:SerializedName("categoryName")
	val categoryName: String? = null,

	@field:SerializedName("agendaId")
	val agendaId: Int? = null,

	@field:SerializedName("presentation")
	val presentation: String? = null,

	/*@field:SerializedName("dominantColor")
	val dominantColor: Any? = null,*/

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("multipleFile")
	val multipleFile: String? = null,

	@field:SerializedName("fbUrl")
	val fbUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("profileImg")
	val profileImg: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("eventId")
	val eventId: Int? = null,

	@field:SerializedName("isDeactive")
	val isDeactive: Int? = null,

	@field:SerializedName("isRating")
	val isRating: Int? = null,

	@field:SerializedName("shortDescription")
	val shortDescription: String? = null,

	@field:SerializedName("presentationFileName")
	val presentationFileName: String? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("updateTimeMilli")
	val updateTimeMilli: String? = null,

	@field:SerializedName("organiserId")
	val organiserId: Int? = null,

	@field:SerializedName("presentationTitle")
	val presentationTitle: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("instagramUrl")
	val instagramUrl: String? = null,

	@field:SerializedName("shortDesc")
	val shortDesc: String? = null,

	@field:SerializedName("position")
	val position: Int? = null,

	@field:SerializedName("createTimeMilli")
	val createTimeMilli: String? = null,

	/*@field:SerializedName("categoryId")
	val categoryId: Any? = null,*/

	@field:SerializedName("isBookmark")
	val isBookmark: Boolean? = null
)

data class ExhibitorsItemDetail(

	@field:SerializedName("exhibitorId")
	val exhibitorId: Int? = null,

	@field:SerializedName("totalUsers")
	val totalUsers: Int? = null,

	/*@field:SerializedName("sponsorId")
	val sponsorId: Any? = null,*/

	@field:SerializedName("twitterUrl")
	val twitterUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("eventCount")
	val eventCount: Int? = null,

	@field:SerializedName("productVideos")
	val productVideos: String? = null,

	@field:SerializedName("agendaId")
	val agendaId: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("websiteUrl")
	val websiteUrl: String? = null,

	@field:SerializedName("fbUrl")
	val fbUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("isSponsor")
	val isSponsor: Int? = null,

	@field:SerializedName("profileImg")
	val profileImg: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("eventId")
	val eventId: Int? = null,

	@field:SerializedName("aseId")
	val aseId: Int? = null,

	@field:SerializedName("brochure")
	val brochure: String? = null,

	@field:SerializedName("isDeactive")
	val isDeactive: Int? = null,

	@field:SerializedName("isRating")
	val isRating: Int? = null,

	@field:SerializedName("stallNo")
	val stallNo: String? = null,

	@field:SerializedName("linkedUrl")
	val linkedUrl: String? = null,

	@field:SerializedName("brochureFileName")
	val brochureFileName: String? = null,

	@field:SerializedName("users")
	val users: List<UsersItemDetail?>? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("updateTimeMilli")
	val updateTimeMilli: String? = null,

	@field:SerializedName("productImages")
	val productImages: String? = null,

	@field:SerializedName("attendeeEmails")
	val attendeeEmails: String? = null,

	@field:SerializedName("organiserId")
	val organiserId: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("interactiveMapLocationId")
	val interactiveMapLocationId: String? = null,

	@field:SerializedName("smallBannerImage")
	val smallBannerImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("listBannerImage")
	val listBannerImage: String? = null,

	@field:SerializedName("position")
	val position: Int? = null,

	@field:SerializedName("aseCreateTimeMilli")
	val aseCreateTimeMilli: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("createTimeMilli")
	val createTimeMilli: String? = null,

	@field:SerializedName("isBookmark")
	val isBookmark: Boolean? = null
)


data class Engagement(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("isDefault")
	val isDefault: Int? = null,

	@field:SerializedName("orderIndex")
	val orderIndex: Int? = null,

	@field:SerializedName("engagementMetaName")
	val engagementMetaName: String? = null,

	@field:SerializedName("embedCode")
	val embedCode: String? = null,

	@field:SerializedName("svgImage")
	val svgImage: String? = null,

)