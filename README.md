# SimpleScoreboard
As the name implies, a simple scoreboard UI for Android.

You can add new players, increase/decrease player scores, and delete players.

Lollipop version | KitKat version
:-------------------------:|:-------------------------:
![](https://raw.githubusercontent.com/ryan-simon/simple-scoreboard/master/screenshots/SimpleScoreboard_Lollipop.png)  |  ![](https://raw.githubusercontent.com/ryan-simon/simple-scoreboard/master/screenshots/SimpleScoreboard_KitKat.png)
![](https://raw.githubusercontent.com/ryan-simon/simple-scoreboard/master/screenshots/SimpleScoreboard_AddPlayer_Lollipop.png) | ![](https://raw.githubusercontent.com/ryan-simon/simple-scoreboard/master/screenshots/SimpleScoreboard_AddPlayer_KitKat.png)

# Design
SimpleDashboard uses a unique color palette to create a simple, and modern design language.

A lot of inspiration has been taken from the Material Design guidelines.

# Libraries and Techniques Used
Everything but the floating action button is done with Android support libraries. Simple is good!

#### RecyclerView [[link]](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html)
Those familiar with the ViewHolder pattern and its use with ListViews can rejoice in the beautiful flexibility that is RecyclerView. SimpleScoreboard uses this for grid efficiency and the built-in add/remove animations.

RecyclerView incorporates its own version of the ViewHolder pattern to maintain the performance gains, yet allows for much more flexibility.

For example, this flexibility is especially useful in handling click events on different Views within an individual row.

#### CardView [[link]](https://developer.android.com/reference/android/support/v7/widget/CardView.html)
A grid of CardViews makes what would normally be a fairly boring list of data, look a little more exciting.

Using a CardView for showing what amounts to a scorecard also makes a lot of sense :).

#### Toolbar [[link]](http://developer.android.com/reference/android/widget/Toolbar.html)
As Android's replacement for the ActionBar, the Toolbar's greatest strength is its ability to be injected straight into a layout. This allows for an incredible amount of design flexibility.

SimpleDashboard's Toolbar is used for context, and color contrast.

#### Snackbar [[link]](http://developer.android.com/reference/android/support/design/widget/Snackbar.html)
As part of the new Material Design, the Snackbar is sleek alternative for Toast messages.

SimpleScoreboard displays a Snackbar with an undo option after removing a player from the scoreboard.

#### FloatingActionButton (FAB) [[link]](https://github.com/makovkastar/FloatingActionButton)
To make life a little easier, makovkastar's FloatingActionButton builds in a lot of great features from the FAB guidelines.

The FAB in SimpleScoreboard is used to add a new player, and makes for great color contrast against the content.