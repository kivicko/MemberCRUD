(function($){
  var $form = $('#memberInfo');
  $form.on('submit', function(e) {
    e.preventDefault();
    $.ajax({
      url: $form.attr('action'),
      type: 'post',
      data: $form.serialize(),
      success: function(response) {
      alert('successfully submitted')
       console.log('YETER YILDIRIM DEMIROREN')
       alert('HAHHAH')
        if ($(response).find('.has-error').length) {
//          location.reload();
//          $('#modal-add-member').modal('show');
//          $form.replaceWith(response);

          console.log('YETER YILDIRIM DEMIROREN')
        } else {
//          $('#modal-add-member').modal('hide');
//          location.reload();
            alert('dasasad')
            console.log('YETER YILDIRIM DEMIROREN')
        }
      },
      error: function(error) {
        alert('HAYDAR')
        console.log('YETER YILDIRIM DEMIROREN')
      }
  });
}(jQuery))});

    $('#modal-delete-member').on('show.bs.modal', function(e) {
            $(this).find('.btn-ok').attr('id', $(e.relatedTarget).data('href'));
        });


  function onDelete(x) {
             var $elemId = x.id
             $.ajax({
             url: $elemId +'/delete',
             type: 'post',
             success: function(response) {
                $('#modal-delete-member').modal('hide');
                location.reload();
                 }})
           }