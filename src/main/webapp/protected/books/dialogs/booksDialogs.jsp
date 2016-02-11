<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="addBooksModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog" aria-labelledby="addBooksModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3 id="addBooksModalLabel" class="displayInLine">
            <spring:message code="create"/>&nbsp;<spring:message code="book"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="newBookForm" novalidate class="form" role="form">
            <div>

                <div class="form-group">
                    <input type="text" class="form-control" required autofocus ng-model="book.bookName"
                           name="bookName"
                           placeholder="<spring:message code='books.name'/>"/>

                    <div class="help-inline">
							<span class="text-error"
                                  ng-show="displayValidationError && newBookForm.bookName.$error.required">
									<spring:message code="books.form.required.bookName"/>
							</span>
                    </div>
                </div>


                <div>
                    <div class="form-group">
                        {{book.bookCategory}}
                        <bs-dropdown2 data-menu-type="button"
                                      select-val="book.bookCategory = selectedVal"
                                      preselected-item="book.bookCategory"
                                      data-dropdown-data="book_categories"
                                      emptyval="Seçiniz" css="form-control"></bs-dropdown2>
                        <div class="help-inline">
                            <span class="text-error"
                                          ng-show="displayValidationError && updateBookForm.bookCategory.$error.required">
								<spring:message code="books.form.required.bookCategory"/>
						    </span>

                        </div>

                    </div>

                </div>
                <br>

                <div class="form-group">
                    <input class="form-control" type="text" autofocus required ng-model="book.bookISBN"
                           name="bookISBN" id="bookISBN1"
                           placeholder="Book ISBN"/>

                    <div class="help-inline">
						<span class="text-error"
                              ng-show="displayValidationError && updateBookForm.bookISBN.$error.required">
								<spring:message code="books.form.required.bookISBN"/>
						</span>
                    </div>
                </div>


                <div>
                    <div class="input-append">
                        <input type="text" required ng-model="book.bookAuthor"
                               name="bookAuthor" class="form-control"
                               placeholder="Book Author"/>
                        <div class="help-inline">
                            <span class="text-error"
                                          ng-show="displayValidationError && updateBookForm.bookAuthor.$error.required">
								<spring:message code="books.form.required.authorName"/>
						    </span>
                        </div>
                    </div>


                </div>
                <div>
                    <div class="input-group">
                        <input type="text" numbersonly required ng-model="book.cost" placeholder="Book Price" name="cost" class="form-control"/>
                        <div class="help-inline">
                            <span class="text-error"
                                          ng-show="displayValidationError && updateBookForm.cost.$error.required">
								<spring:message code="books.form.required.cost"/>
						    </span>
                        </div>
                    </div>

                </div>
                <hr>
                <div>
                    <div class="input-group">
                        <label class="text-warning">[ {{captchaOrg}} ] (Please type this word to below)
                        </label>
                        <input type="text" ng-valid="captchaOrg!=captchaAgain" required ng-model="captchaAgain"
                               name="capt" class="form-control"/>

                        <div class="help-inline">
                            <span class="text-error"
                                          ng-show="displayValidationError && newBookForm.capt.$error.required && (captchaOrg!=captchaAgain)">
								Captcha incorrect!!!
						    </span>

                        </div>
                    </div>

                </div>
                <input type="submit" class="btn btn-inverse"
                       ng-click="createBook(newBookForm);"
                       value='<spring:message code="create"/>'/>
                <button class="btn btn-inverse" data-dismiss="modal"
                        ng-click="exit('#addBooksModal');" aria-hidden="true">
                    <spring:message code="cancel"/>
                </button>
            </div>
        </form>
    </div>
	<span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit"> <spring:message code="request.error"/>
	</span>
</div>

<div id="updateBooksModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog" aria-labelledby="updateBooksModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="updateBooksModalLabel" class="displayInLine">
            <spring:message code="update"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="updateBookForm" novalidate class="form" style="width: 100% !important;">
            <input type="hidden" required ng-model="book.id" name="id"
                   value="{{book.id}}"/>

            <div>
                <div class="form-group">
                    <label for="bookName">Book Name</label>
                    <input class="form-control" type="text" autofocus required ng-model="book.bookName"
                           name="bookName" id="bookName"
                           placeholder="<spring:message code='books.name'/> "/>
                    <div class="help-inline">
                        <label> <span class="text-alert"
                                      ng-show="displayValidationError && updateBookForm.bookName.$error.required">
								<spring:message code="books.form.required.bookName"/>
						</span>
                        </label>
                    </div>
                </div>

            </div>
            <div>
                <div class="form-group">
                        <label for="bookCategory2"><spring:message code="books.category"/> </label>
                        <span id="bookCategory2" class="text-info"><i class="fa fa-list"></i>&nbsp;{{book.bookCategory}}
                        <bs-dropdown2 data-menu-type="button"
                                      select-val="book.bookCategory = selectedVal"
                                      preselected-item="book.bookCategory"
                                      data-dropdown-data="book_categories"
                                      emptyval="Seçiniz" css="form-control"></bs-dropdown2>
                            </span>
                    <div class="help-inline">
                        <label> <span class="alert alert-error"
                                      ng-show="displayValidationError && updateBookForm.bookCategory.$error.required">
								<spring:message code="books.form.required.bookCategory"/>
						</span>
                        </label>
                    </div>
                </div>

            </div>
            <div>
                <div class="form-group">
                    <label for="bookISBN">Book ISBN</label>
                    <input class="form-control" type="text" autofocus required ng-model="book.bookISBN"
                           name="bookISBN" id="bookISBN"
                           placeholder="<spring:message code='books.isbn'/> "/>
                </div>
                <div class="input-append">
                    <label> <span class="alert alert-error"
                                  ng-show="displayValidationError && updateBookForm.bookISBN.$error.required">
								<spring:message code="books.form.required.bookCategory"/>
						</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-group">
                    <label for="bookAuthor"><spring:message code="books.isbn"/> </label>
                    <input type="text" required ng-model="book.bookAuthor"
                           name="bookAuthor" id="bookAuthor" class="form-control"
                           placeholder="<spring:message code='sample.email'/> "/>
                </div>

                <div class="input-append">
                    <label> <span class="alert alert-error"
                                  ng-show="displayValidationError && updateBookForm.bookAuthor.$error.required">
								<spring:message code="books.form.required.authorName"/>
						</span>
                    </label>
                </div>
            </div>
            <div>
                <div class="form-group">
                    <label for="cost"><spring:message code="books.cost"/> </label>
                    <input type="text" numbersonly required ng-model="book.cost" id="cost" name="cost" class="form-control"/>
                </div>
                <div class="input-append">
                    <label> <span class="alert alert-error"
                                  ng-show="displayValidationError && updateBookForm.cost.$error.required">
								<spring:message code="books.form.required.cost"/>
						</span>
                    </label>
                </div>
            </div>
            <input type="submit" class="btn btn-inverse pull-right"
                   ng-click="updateBook(updateBookForm);"
                   value='<spring:message code="update"/>'/>
            <button class="btn btn-inverse pull-left" data-dismiss="modal"
                    ng-click="exit('#updateBooksModal');" aria-hidden="true">
                <spring:message code="cancel"/>
            </button>

        </form>
    </div>
	<span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit"> <spring:message code="request.error"/>
	</span>
</div>

<div id="deleteBooksModal" class="modal hide fade in centering"
     role="dialog" aria-labelledby="searchBooksModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="deleteBooksModalLabel" class="displayInLine">
            <spring:message code="delete"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="deleteBookForm" novalidate>
            <p>
                <spring:message code="delete.confirm"/>
                :&nbsp;{{book.bookName}}?
            </p>

            <input type="submit" class="btn btn-inverse" ng-click="deleteBook();"
                   value='<spring:message code="delete"/>'/>
            <button class="btn btn-inverse" data-dismiss="modal"
                    ng-click="exit('#deleteBooksModal');" aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
	<span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit"> <spring:message code="request.error"/>
	</span>
</div>


