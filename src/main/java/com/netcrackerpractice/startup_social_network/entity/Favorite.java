package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.FavoriteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Favorites")
public class Favorite {
   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator( name = "UUID",
           strategy = "org.hibernate.id.UUIDGenerator")
   private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = "resumeSkills", allowSetters = true)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_favorite_account")
    @JsonIgnoreProperties(value = "id_favorite_account", allowSetters = true)
    private Account favoriteAccount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getFavoriteAccount() {
        return favoriteAccount;
    }

    public void setFavoriteAccount(Account favoriteAccount) {
        this.favoriteAccount = favoriteAccount;
    }
}
