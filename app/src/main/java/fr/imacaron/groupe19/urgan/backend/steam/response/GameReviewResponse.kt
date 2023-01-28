package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName

data class GameReviewResponse(
    @SerializedName("success"       ) var success      : Int?               = null,
    @SerializedName("query_summary" ) var querySummary : QuerySummary?      = QuerySummary(),
    @SerializedName("reviews"       ) var reviews      : ArrayList<Reviews> = arrayListOf(),
    @SerializedName("cursor"        ) var cursor       : String?            = null
) {

    data class QuerySummary (

        @SerializedName("num_reviews"       ) var numReviews      : Int?    = null,
        @SerializedName("review_score"      ) var reviewScore     : Int?    = null,
        @SerializedName("review_score_desc" ) var reviewScoreDesc : String? = null,
        @SerializedName("total_positive"    ) var totalPositive   : Int?    = null,
        @SerializedName("total_negative"    ) var totalNegative   : Int?    = null,
        @SerializedName("total_reviews"     ) var totalReviews    : Int?    = null

    )

    data class Reviews (

        @SerializedName("recommendationid"            ) var recommendationid         : String?  = null,
        @SerializedName("author"                      ) var author                   : Author?  = Author(),
        @SerializedName("language"                    ) var language                 : String?  = null,
        @SerializedName("review"                      ) var review                   : String?  = null,
        @SerializedName("timestamp_created"           ) var timestampCreated         : Int?     = null,
        @SerializedName("timestamp_updated"           ) var timestampUpdated         : Int?     = null,
        @SerializedName("voted_up"                    ) var votedUp                  : Boolean? = null,
        @SerializedName("votes_up"                    ) var votesUp                  : Int?     = null,
        @SerializedName("votes_funny"                 ) var votesFunny               : Int?     = null,
        @SerializedName("weighted_vote_score"         ) var weightedVoteScore        : String?  = null,
        @SerializedName("comment_count"               ) var commentCount             : Int?     = null,
        @SerializedName("steam_purchase"              ) var steamPurchase            : Boolean? = null,
        @SerializedName("received_for_free"           ) var receivedForFree          : Boolean? = null,
        @SerializedName("written_during_early_access" ) var writtenDuringEarlyAccess : Boolean? = null,
        @SerializedName("hidden_in_steam_china"       ) var hiddenInSteamChina       : Boolean? = null,
        @SerializedName("steam_china_location"        ) var steamChinaLocation       : String?  = null

    ) {

        data class Author (

            @SerializedName("steamid"                 ) var steamid              : String? = null,
            @SerializedName("num_games_owned"         ) var numGamesOwned        : Int?    = null,
            @SerializedName("num_reviews"             ) var numReviews           : Int?    = null,
            @SerializedName("playtime_forever"        ) var playtimeForever      : Int?    = null,
            @SerializedName("playtime_last_two_weeks" ) var playtimeLastTwoWeeks : Int?    = null,
            @SerializedName("playtime_at_review"      ) var playtimeAtReview     : Int?    = null,
            @SerializedName("last_played"             ) var lastPlayed           : Int?    = null

        )

    }

}
