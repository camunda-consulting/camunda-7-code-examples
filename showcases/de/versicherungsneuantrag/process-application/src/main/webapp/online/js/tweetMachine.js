/*
 * jQuery TweetMachine v0.2.1b
 * GitHub: https://github.com/ryangiglio/jquery-tweetMachine
 * Copyright (c) 2013 Ryan Giglio (@ryangiglio)
 */
(function ($) {
    // Plugin body
	$.fn.tweetMachine = function (query, options, callback) {
        // For each instance of the plugin
		$(this).each(function () {
			var settings, tweetMachine;
            // If this.tweetMachine is already initialized, just change the settings
			if (this.tweetMachine) {
                // Overwrite the initialized/default settings
				settings = $.extend(this.tweetMachine.settings, options);
				this.tweetMachine.settings = settings;

                // If a new query has been passed
				if (query) {
                    // Replace the old query
					this.tweetMachine.query = query;
				}
                // If a tweet interval is already set up
				if (this.tweetMachine.interval) {
                    // Refresh now so the new settings can kick in  
					this.tweetMachine.refresh();
				}
                // If a new callback was passed
				if (callback) {
                    // Replace the old callback
					this.tweetMachine.callback = callback;
				}
			} else { // It's not initialized, so let's do that
				settings = $.extend({
					backendScript:  'ajax/getFromTwitter.php', // Path to your backend script that holds your Twitter credentials and calls the API
					endpoint:       'search/tweets', // Twitter API endpoint to call. Currently only search/tweets is supported
					user_name:		'jason_alvis', // Set your username
					include_retweets: true, // Set to true or false if you want to include retweets
					exclude_replies: false, // Set to true or false if you want to exclude replies
					rate:           5000, // Rate in ms to refresh the tweets. Any higher than 5000 for search/tweets will get you rate limited
					limit:          5, // Number of tweets to display at a time
					autoRefresh:    true, // CURRENTLY REQUIRED. Auto-refresh the tweets
					animateOut:     false, // NOT YET SUPPORTED. Animate out old tweets.
					animateIn:      true, // Fade in new tweets.
					tweetFormat: "<li><p class='content'></p><b><a href='' class='time'></a></b></li>", // Format for each tweet
					localization: { // Verbiage to use for timestamps
						seconds:    'seconds ago',
						minute:     'a minute ago',
						minutes:    'minutes ago',
						hour:       'an hour ago',
						hours:      'hours ago',
						day:        'a day ago',
						days:       'days ago'
					},
					filter:         false // Function to filter tweet results.
				}, options);
				this.tweetMachine = {
					settings: settings, // Set the settings object
					query: query, // Set the query to search for
					interval: false, // This will hold the refresh interval when it is created
					container: this, // Set the object that contains the tweets
					lastTweetID: null, // This will hold the ID of the last tweet displayed
					callback: callback, // This callback will run after each refresh

                    /*
                     * Function to generate a relative timestamp from Twitter's time string
                     */
					relativeTime: function (timeString) {
						var delta, parsedDate, r;
                        
                        // Create a Date object
						parsedDate = Date.parse(timeString);

                        // Get the number of seconds ago that the tweet was created
						delta = (Date.parse(Date()) - parsedDate) / 1000;
                        
                        // String to hold the relative time
						r = '';

                        // If it was less than a minute ago
						if (delta < 60) {
							r = delta + " " + settings.localization.seconds;
                        // If it was less than 2 minutes ago
						} else if (delta < 120) {
							r = settings.localization.minute;
                        // If it was less than 45 minutes ago
						} else if (delta < (45 * 60)) {
							r = (parseInt(delta / 60, 10)).toString() + " " + settings.localization.minutes;
                        // If it was less than 90 minutes ago
						} else if (delta < (90 * 60)) {
							r = settings.localization.hour;
                        // If it was less than a day ago
						} else if (delta < (24 * 60 * 60)) {
							r = '' + (parseInt(delta / 3600, 10)).toString() + " " + settings.localization.hours;
                        // If it was less than 2 days ago
						} else if (delta < (48 * 60 * 60)) {
							r = settings.localization.day;
						} else {
							r = (parseInt(delta / 86400, 10)).toString() + " " + settings.localization.days;
						}
						return r;
					},

                    /*
                     * Function to update the timestamps of each tweet
                     */
					updateTimestamps: function () {
						var tweetMachine;
						tweetMachine = this;
                        // Loop over each timestamp
						$(tweetMachine.container).find('.time').each(function () {
							var originalTime, timeElement;

                            // Save a reference to the time element
							timeElement = $(this);
                            
                            // Get the original time from the data stored on the timestamp
							originalTime = timeElement.data('timestamp');

                            // Generate and show a new time based on the original time
							timeElement.html(tweetMachine.relativeTime(originalTime));
						});
					},
					
                    /*
                     * Function to parse the text of a tweet and and add links to links, hashtags, and usernames
                     */
					parseText: function (text) {
                        // Links
						text = text.replace(/[A-Za-z]+:\/\/[A-Za-z0-9-_]+\.[A-Za-z0-9-_:%&\?\/.=]+/g, function (m) {
							return '<a href="' + m + '" target="_blank">' + m + '</a>';
						});
                        // Usernames
						text = text.replace(/@[A-Za-z0-9_]+/g, function (u) {
							return '<a href="http://twitter.com/#!/' + u.replace(/^@/, '') + '" target="_blank">' + u + '</a>';
						});
                        // Hashtags
						text = text.replace(/#[A-Za-z0-9_\-]+/g, function (u) {
							return '<a href="http://twitter.com/#!/search?q=' + u.replace(/^#/, '%23') + '" target="_blank">' + u + '</a>';
						});
						return text;
					},

                    /*
                     * Function to build the tweet as a jQuery object
                     */
					buildTweet: function (tweet) {
						var tweetMachine, tweetObj;
						tweetMachine = this;

                        // Create the tweet from the tweetFormat setting
						tweetObj = $(tweetMachine.settings.tweetFormat);
                        
                        // Set the avatar. NOTE: reasonably_small is Twitter's suffix for the largest square avatar that they store
						tweetObj.find('.avatar')
                            .attr('src', tweet.user.profile_image_url.replace("normal", "reasonably_small"));

                        // Set the username
						tweetObj.find('.username')
                            .attr('href', "http://twitter.com/" + tweet.user.screen_name)
                            .attr('target', '_blank')
                            .html("" + tweet.user.screen_name);

                        // Set the timestamp
						tweetObj.find('.time')
                            .attr('href', "http://twitter.com/" + tweet.user.screen_name + "/status/" + tweet.id_str)
                            .attr('target', '_blank')
                            .html(tweetMachine.relativeTime(tweet.created_at))
                            // Save the created_at time as jQuery data so we can update it later
                            .data('timestamp', tweet.created_at);

                        // Set the text
						tweetObj.find('.content')
                            .html(tweetMachine.parseText(tweet.text));

                        // If we are animating in the new tweets
						if (tweetMachine.settings.animateIn) {
                            // Set the opacity to 0 so it can fade in
							tweetObj.css('opacity', '0');
						}

						return tweetObj;
					},

                    /*
                     * Function to handle the reloading of tweets
                     */
					refresh: function (firstLoad) {
						var queryParams, tweetMachine;
						tweetMachine = this;

                        // If it is the first load or we're refreshing automatically
						if (firstLoad || tweetMachine.settings.autoRefresh) {
                            // Set the query parameters that the endpoint needs
                            
                            /*
                             * Twitter feed for search through tweets only
                             * API Reference: https://dev.twitter.com/docs/api/1.1/get/search/tweets
                             */
                            if (tweetMachine.settings.endpoint === "search/tweets") {
                                queryParams = {
                                    q: tweetMachine.query,
                                    count: (this.settings.requestLimit) ? this.settings.requestLimit: this.settings.limit,
                                    since_id: tweetMachine.lastTweetID
                                };
                            }

                            /*
                             * Twitter feed for username only
                             * API Reference: https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline
                             */							
                            if (tweetMachine.settings.endpoint === "statuses/user_timeline") {
                                queryParams = {
                                    screen_name: settings.user_name,
                                    count: (this.settings.requestLimit) ? this.settings.requestLimit: this.settings.limit,
                                    include_rts: settings.include_retweets,
                                    exclude_replies: settings.exclude_replies
                                };
                            }

                            // Call your backend script to get JSON back from Twitter
							$.getJSON(tweetMachine.settings.backendScript, {
								endpoint: tweetMachine.settings.endpoint,
								queryParams: queryParams
							}, function (tweets) {
								var tweetsDisplayed;

                                // If we got a response from Twitter
                                if ( tweets[0] ) {
                                    // If there is an error message
                                    if ( tweets[0].message ) {
                                        // If there is already an error displayed
                                        if ( $('.twitter-error').length ) {
                                            // Update the error message
                                            $('.twitter-error').html('<p class="tweet-machine-error">Error  ' + tweets[0].code + ': ' + tweets[0].message + '</p>');
                                        }
                                        else { // There isn't an error displayed yet
                                            // Display an error message above the container
                                            $(tweetMachine.container).before('<p class="twitter-error">Error  ' + tweets[0].code + ': ' + tweets[0].message + '</p>');
                                        }
                                    }
                                    // There are tweets
                                    else {
                                        // If there was an error before
                                        if ( $('.twitter-error').length ) {
                                            // Remove it
                                            $('.twitter-error').remove();
                                        }

                                        // Reverse them so they are added in the correct order
                                        tweets.reverse();

                                        // Count the number of tweets displayed
                                        tweetsDisplayed = 0;

                                        // Loop through each tweet
                                        $.each(tweets, function () {
                                            var tweet, tweetObj;
                                            tweet = this;

                                            // If there is no filter, or this tweet passes the filter
                                            if (!tweetMachine.settings.filter || tweetMachine.settings.filter(this)) {
                                                // Build the tweet as a jQuery object
                                                tweetObj = tweetMachine.buildTweet(tweet);

                                                // If there are already tweets on the screen
                                                if (!firstLoad) {

                                                    // If we are animating out the old tweets
                                                    if (tweetMachine.settings.animateOut) {
                                                        /*
                                                         * TODO Support this feature
                                                         */
                                                    } else { // We are not animating the old tweets
                                                        // Remove them
                                                        $(tweetMachine.container).children(':last-child').remove();
                                                    }
                                                }

                                                // Prepend the new tweet
                                                $(tweetMachine.container).prepend(tweetObj);

                                                // If we are animating in the new tweets
                                                if (tweetMachine.settings.animateIn) {
                                                    // Fade in the new tweet
                                                    /*
                                                     * TODO Figure out why .fadeIn() doesn't work
                                                     */
                                                    $(tweetMachine.container).children(':first-child').animate({
                                                        opacity: 1
                                                    });
                                                }

                                                // Increment the tweets diplayed
                                                tweetsDisplayed++;
                                                
                                                // Save this tweet ID so we only get newer noes
                                                tweetMachine.lastTweetID = tweet.id_str;
                                                
                                                // If we've reached the limit of tweets to display
                                                if (tweetsDisplayed > tweetMachine.settings.limit) {
                                                    // Quit the loop
                                                    return false;
                                                }
                                            }
                                        });
                                    }
                                }
								//Callback function
								if (typeof tweetMachine.callback === "function") {
									if(typeof tweets === 'undefined' || typeof tweetsDisplayed === 'undefined' ) {
										tweets = null;
										tweetsDisplayed = 0;
									}
									tweetMachine.callback(tweets, tweetsDisplayed);
								}
							});
						}
                        /* TODO: Implement an "x new Tweets, click to refresh" link if auto refresh is turned off
                        else {
                        }
                        */
					},

                    // Start refreshing
					start: function () {
						var tweetMachine;
						tweetMachine = this;

                        // If there's no interval yet
						if (!this.interval) {
                            // Create an interval to refresh after the rate has passed
							this.interval = setInterval(function () {
								tweetMachine.refresh();
							}, tweetMachine.settings.rate);
                            // Start refreshing with the firstLoad flag = true
							this.refresh(true);
						}
					},

                    // Stop refreshing
					stop: function () {
						var tweetMachine;
						tweetMachine = this;

                        // If there is an interval
						if (tweetMachine.interval) {
                            // Clear it
							clearInterval(tweetMachine.interval);
                            
                            // Remove the reference to it
							tweetMachine.interval = false;
						}
					},

                    // Clear all tweets
					clear: function () {
						var tweetMachine;
						tweetMachine = this;
                        
                        // Remove all tweets
						$(tweetMachine.container).find('.tweet').remove();

                        // Set the lastTweetID to null so we start clean next time
						tweetMachine.lastTweetID = null;
					}
				};

                // Save a global tweetMachine object
				tweetMachine = this.tweetMachine;

                // Create an interval to update the timestamps
				this.timeInterval = setInterval(function () {
					tweetMachine.updateTimestamps();
				}, tweetMachine.settings.rate);

                // Start the Machine!
				this.tweetMachine.start();
			}
		});
	};
})(jQuery);