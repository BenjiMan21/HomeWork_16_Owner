package com.nikiforov;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты для вакансии")
@Owner("Nikiforov")
@Tag("steam_tests")
public class JobTest extends TestBase {
    String game = "Loop Hero";
    String price = "14,79€";

    @Test
    @DisplayName("Смена языка на главной странице")
    void changeLanguage() {
        step("Смена языка магазина на главной странице", ()-> {
            open("");
            $("#language_pulldown").click();
            $("#language_dropdown").$(byText("Русский (Russian)")).click();
        });
        step("Проверка смены языка", ()-> {
            $(".supernav_container").shouldHave(text("МАГАЗИН"));
        });
    }

    @Test
    @DisplayName("Проверка перехода на страницу информации")
    void jumpToSupportPage() {
        step("Кликаем на ИНФОРМАЦИЯ в меню навигации", ()-> {
            open("");
            $(".supernav_container").$(byText("About")).click();
        });
        step("Проверка перехода на страницу информации", ()-> {
            $("#about_greeting")
                    .shouldHave(text("Steam is the ultimate destination for playing, discussing, and creating games."));
        });
    }

    @Test
    @DisplayName("Проверка строки поиска")
    void searchGame() {
        step("Пишем название видеоигры в строке поиска", ()-> {
            open("");
            $("#store_nav_search_term").setValue(game).pressEnter();
        });
        step("Проверка поиска игры", ()-> {
            $(".search_name").shouldHave(text(game));
        });
    }

    @Test
    @DisplayName("Поверка цены видеоигры")
    void checkGamePrice() {
        step("Пишем название видеоигры в строке поиска", ()-> {
            open("");
            $("#store_nav_search_term").setValue(game).pressEnter();

        });
        step("Проверка цены видеоигры без скидки", ()-> {
            $(".responsive_search_name_combined").shouldHave(text(price));
        });
    }

    @Test
    @DisplayName("Добавление видеоигры в корзину")
    void addGameToCart() {
        step("Пишем название видеоигры в строке поиска", ()-> {
            open("");
            $("#store_nav_search_term").setValue(game).pressEnter();
        });
        step("Выбираем видеоигру из списка найденных и переходим на её страницу", ()-> {
            $(".responsive_search_name_combined").click();
        });
        step("Добавляем видеоигру в корзину", ()-> {
            $(".btn_addtocart").click();
        });
        step("Проверяем что видеоигра находится в корзине", ()-> {
            $(".DialogContent_InnerWidth").shouldHave(text("View My Cart (1)")).click();
            $(".page_content_ctn").shouldHave(text(game));
        });
    }

    @Test
    @DisplayName("Удаляем видеоигру из корзины")
    void deleteGameFromCart() {
        step("Пишем название видеоигры в строке поиска", ()-> {
            open("");
            $("#store_nav_search_term").setValue(game).pressEnter();

        });
        step("Выбираем видеоигру из списка найденных и переходим на её страницу", ()-> {
            $(".responsive_search_name_combined").click();
        });
        step("Добавляем видеоигру в корзину", ()-> {
            $(".btn_addtocart").click();
        });
        step("Затем заходим в корзину и удаляем видеоигру из корзины", ()-> {
            $(".DialogContent_InnerWidth").shouldHave(text("View My Cart (1)")).click();
            $("._33j4SwfO2YH9eI6qV9BKaL").shouldHave(text("Remove")).click();
        });
        step("Проверяем что товар удалён из корзины", ()-> {
            $("._17GFdSD2pc0BquZk5cejg8").shouldHave(text("Your cart is empty."));
        });
    }


}
