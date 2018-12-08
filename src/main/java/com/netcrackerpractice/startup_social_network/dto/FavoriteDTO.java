package com.netcrackerpractice.startup_social_network.DTO;

import com.netcrackerpractice.startup_social_network.entity.enums.FavoriteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteDTO {
    private UUID id;

    private UUID accountId;

    private FavoriteTypeEnum favoriteType;

}
