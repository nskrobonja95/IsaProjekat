<label>Select seats</label>
<div class="form-group">
    <div class="radio">
        <label>
           <input type="radio" ng-model="formData.type" value="xbox" checked>
           I like XBOX
        </label>
    </div>
    <div class="radio">
        <label>
            <input type="radio" ng-model="formData.type" value="ps">
            I like PS4
        </label>
    </div>
</div>

<div class="form-group row">
<div class="col-xs-12" class="btnbox-center">
    <a ui-sref="form.profile" class="btn btn-info">
     <span class="glyphicon glyphicon-circle-arrow-left"></span> Back
    </a>
    <a ui-sref="form.payment" class="btn btn-right btn-info">
    Next <span class="glyphicon glyphicon-circle-arrow-right"></span>
    </a>
</div>
</div>