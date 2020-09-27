# repository

This is an unfinished basic retrofit search fragment. Uploaded with the hopes that someone will do the small change needed to finish it

It is supposed to be designed to allow the user to search for content from an api with each letter typed.

I have a BroadcastReceiver set up so that the fragment can respond every time a character is pressed however, i dont know how to use the notifyDataSetChanged() function properly to ensurethe recyclerview changes content on every broadcast.

Notes:
# the https://reqres.in/api/ does pages based on numbers (more than likely for simplicity reasons) so there is an intOrString() function that transforms any key pressed to 1 if the character pressed is an int or 2 if its not
# the contentAdapter.notifyDataSetChanged() function inside of exploreFragment.kt does nothing. i think i have to somehow use the notifyDataSetChanged() on the adapter somehow or something like that (i could be wrong)
