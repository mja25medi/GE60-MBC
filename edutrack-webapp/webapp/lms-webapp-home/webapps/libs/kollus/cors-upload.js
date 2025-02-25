/**
 * Kollus Cors Upload
 *
 * required library : jQuery, bootstrap, ua-parser-js
 */

/**
 * Show Instant Message.
 *
 * @param {string} type - success|info|warning|danger
 * @param {string} message
 * @param {number} delayTime
 * @param {number} slideUptime -
 */
function showAlert(type, message, delayTime, slideUpTime){
    var alertMessage = $('<div class="alert alert-' + type + '">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button>' + message + '</div>');

    delayTime = delayTime || 5000;
    slideUptime = slideUpTime || 200;

    alertMessage.delay(delayTime).slideUp(slideUpTime);

    $('#alert_message').append(alertMessage);
}


/**
 * Upload event handler
 */
$(document).on('click', 'button[data-action=upload-file]', function(e) {
    e.preventDefault();
    e.stopPropagation();

    var thisTag = $(this),
        closestForm = thisTag.closest('form'),
        uploadFile = closestForm.find('input[type=file][name=upload-file]'),
        categoryKey = closestForm.find('input[name=category_key]'),
        progress = closestForm.find('div.form-group div.progress'),
        title = $("#cntsNm",parent.document),
        progressBar,
        progressValue = 0,
        formData = new FormData(),
        ajaxGetdata = {
            get_upload_url: 1,
            category_key: categoryKey.val()
        },
        repeatorId,
        uploadFileKey = '',
        uploadFileName = '';

    if (closestForm.find('input[name=is_encryption_upload]').prop('checked')) {
        ajaxGetdata.is_encryption_upload = 1;
    }
    if (closestForm.find('input[name=is_audio_upload]').prop('checked')) {
        ajaxGetdata.is_audio_upload = 1;
    }

    if (title.val().length) {
        ajaxGetdata.title = title.val();
    }

    // get upload_url
    $.post(
        closestForm.attr('action'),
        ajaxGetdata,
        function(data) {

            // validate data
            if (('error' in data && data.error) ||
                !('result' in data) ||
                !('upload_url' in data.result) ||
                !('progress_url' in data.result)) {

                showAlert('danger',
                    ('message' in data ? data.message : 'Api response error.')
                );
                return;
            }

            if (typeof uploadFile.prop('files')[0] == 'undefined') {
                showAlert('danger', 'Please select a file to upload.');
                uploadFile.focus();
                return;
            }

            uploadFileKey = data.result.upload_file_key;
            uploadFileName = uploadFile.val();

            // create form data
            formData.append('upload-file', uploadFile.prop('files')[0]);
            formData.append('disable_alert', 1);
            formData.append('accept', 'application/json');

            // create progress bar
            progressBar = progress.find('.progress-bar');

            if (!progressBar.length) {
                progressBar = $(
                    '<div class="progress-bar" role="progessbar" aria-valuenow="0" ' +
                    'aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;">0%</div>'
                );
                progress.append(progressBar);
                progress.css('display', 'block');
            } else {
                progressBar.attr('aria-valuenow', 0);
                progressBar.width('0%');
                progressBar.text('0%');
                progress.fadeIn(500);
            }

            // disable button
            uploadFile.attr('disabled', true);
            thisTag.button('loading');

            // upload file
            $.ajax({
                type: 'post',
                url: data.result.upload_url,
                data: formData,
                dataType: 'json',
                cache: false,
                contentType: false,
                processData: false,
                xhr: function() {
                    var xhr = new XMLHttpRequest();
                    //xhr.withCredentials = true;
                    showAlert('info', 'Uploading file ...');

                    repeatorId = setInterval(function() {
                        $.get(data.result.progress_url, function(data) {
                            // old regacy
                            // if ('progress' in data) {
                            //     progressValue = Math.ceil(parseInt(data.progress, 10));
                            // } else
                            if ('result' in data &&
                                'progress' in data.result) {
                                progressValue = Math.ceil(parseInt(data.result.progress, 10));
                            }
                            if (progressValue > 0) {
                                progressBar.attr('aria-valuenow', progressValue);
                                progressBar.width(progressValue + '%');
                                progressBar.text(progressValue + '%');
                            }
                        });
                    }, 1000);

                    return xhr;
                },
                success: function(data, textStatus, jqXHR) {

                    if ('error' in data && data.error) {
                        showAlert('danger',
                            ('message' in data ? data.message : 'Api response error.')
                        );
                        return;

                    } else if ('message' in data) {
                        showAlert('success', data.message);
                    }

                    progressBar.attr('aria-valuenow', 100);
                    progressBar.width(100 + '%');
                    progressBar.text(100 + '%');
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    showAlert('danger', textStatus);
                },
                complete: function(jqXHR, textStatus) {
                    clearInterval(repeatorId);
                    uploadFile.val('').clone(true);
                    thisTag.button('reset');
                    uploadFile.attr('disabled', false);
                    progress.delay(2000).fadeOut(500);

        			$("#uldFileKey",parent.document).val(uploadFileKey);
        			$("#fileNm",parent.document).val(getFileName(uploadFileName));
        			$("#playerDiv",parent.document).val("kollus");
        			$("#media-file-name",parent.document).html(getFileName(uploadFileName));
        			$("#media-file-upload-button",parent.document).hide();
        			parent.modalBoxClose();
                }
            });
        },  // end function(data)
        'json'
    );  // end $.post()
});
