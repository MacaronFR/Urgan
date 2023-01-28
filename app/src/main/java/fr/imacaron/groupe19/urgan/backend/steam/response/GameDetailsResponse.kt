package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : Data?    = Data()
) {
    data class Data(
        @SerializedName("type"                    ) var type                 : String?                  = null,
        @SerializedName("name"                    ) var name                 : String?                  = null,
        @SerializedName("steam_appid"             ) var steamAppid           : Int?                     = null,
        @SerializedName("required_age"            ) var requiredAge          : Int?                     = null,
        @SerializedName("is_free"                 ) var isFree               : Boolean?                 = null,
        @SerializedName("dlc"                     ) var dlc                  : ArrayList<Int>           = arrayListOf(),
        @SerializedName("detailed_description"    ) var detailedDescription  : String?                  = null,
        @SerializedName("about_the_game"          ) var aboutTheGame         : String?                  = null,
        @SerializedName("short_description"       ) var shortDescription     : String?                  = null,
        @SerializedName("supported_languages"     ) var supportedLanguages   : String?                  = null,
        @SerializedName("header_image"            ) var headerImage          : String?                  = null,
        @SerializedName("website"                 ) var website              : String?                  = null,
        //@SerializedName("pc_requirements"         ) var pcRequirements       : PcRequirements?          = PcRequirements(),
        //@SerializedName("mac_requirements"        ) var macRequirements      : PcRequirements?          = PcRequirements(),
        //@SerializedName("linux_requirements"      ) var linuxRequirements    : PcRequirements?          = PcRequirements(),
        @SerializedName("legal_notice"            ) var legalNotice          : String?                  = null,
        @SerializedName("ext_user_account_notice" ) var extUserAccountNotice : String?                  = null,
        @SerializedName("developers"              ) var developers           : ArrayList<String>        = arrayListOf(),
        @SerializedName("publishers"              ) var publishers           : ArrayList<String>        = arrayListOf(),
        @SerializedName("price_overview"          ) var priceOverview        : PriceOverview?           = PriceOverview(),
        @SerializedName("packages"                ) var packages             : ArrayList<Int>           = arrayListOf(),
        @SerializedName("package_groups"          ) var packageGroups        : ArrayList<PackageGroups> = arrayListOf(),
        @SerializedName("platforms"               ) var platforms            : Platforms?               = Platforms(),
        @SerializedName("categories"              ) var categories           : ArrayList<Categories>    = arrayListOf(),
        @SerializedName("genres"                  ) var genres               : ArrayList<Genres>        = arrayListOf(),
        @SerializedName("screenshots"             ) var screenshots          : ArrayList<Screenshots>   = arrayListOf(),
        @SerializedName("movies"                  ) var movies               : ArrayList<Movies>        = arrayListOf(),
        @SerializedName("recommendations"         ) var recommendations      : Recommendations?         = Recommendations(),
        @SerializedName("release_date"            ) var releaseDate          : ReleaseDate?             = ReleaseDate(),
        @SerializedName("support_info"            ) var supportInfo          : SupportInfo?             = SupportInfo(),
        @SerializedName("background"              ) var background           : String?                  = null,
        @SerializedName("background_raw"          ) var backgroundRaw        : String?                  = null,
        @SerializedName("content_descriptors"     ) var contentDescriptors   : ContentDescriptors?      = ContentDescriptors()
    ) {
        data class PackageGroups (
            @SerializedName("name"                      ) var name                    : String?         = null,
            @SerializedName("title"                     ) var title                   : String?         = null,
            @SerializedName("description"               ) var description             : String?         = null,
            @SerializedName("selection_text"            ) var selectionText           : String?         = null,
            @SerializedName("save_text"                 ) var saveText                : String?         = null,
            @SerializedName("display_type"              ) var displayType             : Int?            = null,
            @SerializedName("is_recurring_subscription" ) var isRecurringSubscription : String?         = null,
            @SerializedName("subs"                      ) var subs                    : ArrayList<Subs> = arrayListOf()
        ) {
            data class Subs (
                @SerializedName("packageid"                    ) var packageid                : Int?     = null,
                @SerializedName("percent_savings_text"         ) var percentSavingsText       : String?  = null,
                @SerializedName("percent_savings"              ) var percentSavings           : Int?     = null,
                @SerializedName("option_text"                  ) var optionText               : String?  = null,
                @SerializedName("option_description"           ) var optionDescription        : String?  = null,
                @SerializedName("can_get_free_license"         ) var canGetFreeLicense        : String?  = null,
                @SerializedName("is_free_license"              ) var isFreeLicense            : Boolean? = null,
                @SerializedName("price_in_cents_with_discount" ) var priceInCentsWithDiscount : Int?     = null
            )
        }

        data class PriceOverview (
            @SerializedName("currency"          ) var currency         : String? = null,
            @SerializedName("initial"           ) var initial          : Int?    = null,
            @SerializedName("final"             ) var final            : Int?    = null,
            @SerializedName("discount_percent"  ) var discountPercent  : Int?    = null,
            @SerializedName("initial_formatted" ) var initialFormatted : String? = null,
            @SerializedName("final_formatted"   ) var finalFormatted   : String? = null
        )

        data class PcRequirements (
            @SerializedName("minimum"     ) var minimum     : String? = null,
            @SerializedName("recommended" ) var recommended : String? = null
        )

        data class Platforms (
            @SerializedName("windows" ) var windows : Boolean? = null,
            @SerializedName("mac"     ) var mac     : Boolean? = null,
            @SerializedName("linux"   ) var linux   : Boolean? = null
        )

        data class Categories (
            @SerializedName("id"          ) var id          : Int?    = null,
            @SerializedName("description" ) var description : String? = null
        )

        data class Genres (
            @SerializedName("id"          ) var id          : String? = null,
            @SerializedName("description" ) var description : String? = null
        )

        data class Screenshots (
            @SerializedName("id"             ) var id            : Int?    = null,
            @SerializedName("path_thumbnail" ) var pathThumbnail : String? = null,
            @SerializedName("path_full"      ) var pathFull      : String? = null
        )

        data class Movies (
            @SerializedName("id"        ) var id        : Int?     = null,
            @SerializedName("name"      ) var name      : String?  = null,
            @SerializedName("thumbnail" ) var thumbnail : String?  = null,
            @SerializedName("webm"      ) var webm      : Webm?    = Webm(),
            @SerializedName("mp4"       ) var mp4       : Mp4?     = Mp4(),
            @SerializedName("highlight" ) var highlight : Boolean? = null
        ) {
            data class Webm (
                @SerializedName("480" ) var low : String? = null,
                @SerializedName("max" ) var max : String? = null
            )

            data class Mp4 (
                @SerializedName("480" ) var low : String? = null,
                @SerializedName("max" ) var max : String? = null
            )
        }

        data class Recommendations (
            @SerializedName("total" ) var total : Int? = null
        )

        data class ReleaseDate (
            @SerializedName("coming_soon" ) var comingSoon : Boolean? = null,
            @SerializedName("date"        ) var date       : String?  = null
        )

        data class SupportInfo (
            @SerializedName("url"   ) var url   : String? = null,
            @SerializedName("email" ) var email : String? = null
        )

        data class ContentDescriptors (
            @SerializedName("ids"   ) var ids   : ArrayList<String> = arrayListOf(),
            @SerializedName("notes" ) var notes : String?           = null
        )
    }
}
