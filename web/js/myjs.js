function doLike(postID,userID)
{
    console.log(postID);
    console.log(userID);
    const d={
        userID:userID,
        postID:postID,
        operation:'like'
    };
    $.ajax({
        url:"LikeServlet",
        data:d,
        success: function(data, textStatus, jqXHR){
            console.log(data);
            if(data.trim()=='true')
            {
                let count=$(".like-counter").html();
                count++;
                $('.like-counter').html(count);
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(data);
        }
    });
    
}
