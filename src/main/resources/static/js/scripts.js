// DELETE BUTTON DATA COPY START
    $('#modal-delete-member').on('show.bs.modal', function(e) {
            $(this).find('.btn-ok').attr('id', $(e.relatedTarget).data('href'));
        });
// DELETE BUTTON DATA COPY END

// DELETE START
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
// DELETE END

  // INPUT MASK FOR PHONE NUMBER START
    $("input.phonenumber").mask("(999) 999-99-99");
  // INPUT MASK FOR PHONE NUMBER END

  // ADD Form Validation START
  $(document).ready(function () {
  $("#addmember").validate({
    ignore: ".ignore",
    rules: {
        email: {
            required: true,
            email: true,
            minlength: 5
        },
        name: {
            required: true,
            minlength: 3,
            maxlength: 20,
            lettersonly: true
        },
        surname: {
            required: true,
            minlength: 3,
            maxlength: 20,
            lettersonly: true
        },
        hiddenRecaptcha: {
            required: function () {
                if (grecaptcha.getResponse() == '') {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
    })
    })
  // ADD Form Validation END

  // EDIT Form Validation Start

  $(document).ready(function () {
    $("#editmember").validate({
      ignore: ".ignore",
      rules: {
          email: {
              required: true,
              email: true,
              minlength: 5
          },
          name: {
              required: true,
              minlength: 3,
              maxlength: 20,
              lettersonly: true
          },
          surname: {
              required: true,
              minlength: 3,
              maxlength: 20,
              lettersonly: true
          },
      }
      })
      })

  // EDIT Form Validation END

    // Remove ID from modal if empty START
    $('button.add').on('click', function() {
    var memberModal = $('#modal-add-member');
        if($('.id', memberModal).value == null) {
            $('.id', memberModal).remove();
        }
    })

  // EDIT BUTTON & FORM START
  $('button.edit').on('click', function() {
    var memberModal = $('#modal-edit-member');
    // get values
    var name = $(this).closest('tr').find('td.name').html();
    var lastname = $(this).closest('tr').find('td.lastname').html();
    var email = $(this).closest('tr').find('td.email').html();
    var phonenumber = $(this).closest('tr').find('td.phonenumber').html();
    var id = $(this).closest('tr').find('td.id').html();

    //set values
    $('.name', memberModal).val(name)
    $('.surname', memberModal).val(lastname)
    $('.email', memberModal).val(email)
    $('.phonenumber', memberModal).val(phonenumber)
    $('.id', memberModal).val(id)

    memberModal.modal({show: true});
  });
  // EDIT BUTTON & FORM END

  // RESET MODAL ON CLOSE START
  $('#modal-add-member').on('hidden.bs.modal', function(){
      $(this).find('form')[0].reset();
  });
  $('#modal-edit-member').on('hidden.bs.modal', function(){
      $(this).find('form')[0].reset();
  });
  // RESET MODAL ON CLOSE END
//