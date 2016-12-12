function showProgress(data) {
    
    if (data.status == "begin") {
        document.getElementById('loading_wrapper').style.display = "inline-block";
    } else if (data.status == "success") {
        document.getElementById('loading_wrapper').getElementsByTagName('p')[0].innerHTML = 'Резюме обновлены успешно'
        setTimeout(function () {
            document.getElementById('loading_wrapper').style.display = "none";
        }, 2000);


    }
}

