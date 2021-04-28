function doGet(request) {
    var action = request.parameter.operation;
    console.log(action);

    switch (action) {
        case "createForm":
            return response().json(createAndMoveForm(request.parameter));
        // case "uploadToDrive":
        //   return response().json(moveFormToDrive(folder, request.parameter.formId));
        case "getFormResponses":
            return response().json(getFormResponses(request.parameter.formId));
        default:
            return response().json({
                status: false,
                message: 'Silent!'
            });
    }
}


function getFormResponses(formId) {
    try {
        let responses = FormApp.openById(formId).getResponses();
        let respondentList = responses.map(response => {
            return response.getRespondentEmail();
        });
        return {
            respondentList,
            totalRespondents: respondentList.length
        };
    }
    catch (err) {
        // console.log("No Such Form");
    }
    return [];
}


function createAndMoveForm(params) {
    console.log(params);
    const sessionName = params.sessionName;
    const location = params.location;
    const trainerName = params.trainerName;

    if (sessionName && location && trainerName) {
        const form = createNewForm(sessionName, location, trainerName);

        // Move folder to drive
        const driveId = params.driveId;
        const folder = DriveApp.getFolderById(driveId);
        if(folder){
            return moveFormToDrive(folder, form, driveId);
        }
    }
    return {
        status: false,
        message: "Parameters Missing"
    };
}


function createNewForm(sessionName, location, trainerName) {
    const date = Utilities.formatDate(new Date(), 'IST', 'dd-MMM-yyyy'); // get todays date

    const formTitle = `Spring AU 2021 Jan Batch - ${location} - ${date} - ${sessionName} - ${trainerName} -Trainer Feedback Form`;
    console.log('Creating Form: ', formTitle);

    const form = FormApp.create(formTitle);
    form.setTitle(formTitle);
    form.setDescription('Please give honest feedback to your trainers ');
    form.setAllowResponseEdits(false);
    form.setRequireLogin(true);
    form.setCollectEmail(true);

    const dateOfSession = form.addDateItem().setTitle('Date of the Session')
    dateOfSession.setRequired(true);
    //dateOfSession.createResponse(new Date());

    makeFeedbackChoiceItem(form, 'How would you rate the Training Session?');
    makeFeedbackChoiceItem(form, 'Subject Knowledge/Conceptual clarity of Trainer');
    makeFeedbackChoiceItem(form, 'Training Skills and Competence');
    makeFeedbackChoiceItem(form, 'Presentation Methodology');
    makeFeedbackChoiceItem(form, 'Quality of the Exercise Assigned');
    makeFeedbackChoiceItem(form, 'Questions Handling');

    const rateSession = form.addMultipleChoiceItem();
    rateSession.setTitle('How intense was the Training?');
    rateSession.setChoices([
        rateSession.createChoice('Very intense'),
        rateSession.createChoice('Fairly intense'),
        rateSession.createChoice('Moderate'),
        rateSession.createChoice('Light')
    ]);

    form.addParagraphTextItem().setTitle('Any other comments?').setRequired(true);
    return form;
}


function moveFormToDrive(folder, form, driveId) {
    const file = DriveApp.getFileById(form.getId());
    if (file && form) {
        file.moveTo(folder);
        return {
            status: true,
            message: 'Success',
            driveId,
            formId: form.getId(),
            formName: form.getTitle()
        };
    }
    return {
        status: false,
        message: 'Form/ Drive Not Present!'
    }
}


function makeFeedbackChoiceItem(form, title) {
    const item = form.addMultipleChoiceItem();
    item.setTitle(title);
    item.setChoices([
        item.createChoice('Excellent'),
        item.createChoice('Good'),
        item.createChoice('Average'),
        item.createChoice('Poor')
    ]);
    item.setRequired(true);
}


function response() {
    return {
        json: function (data) {
            return ContentService
                .createTextOutput(JSON.stringify(data))
                .setMimeType(ContentService.MimeType.JSON);
        }
    }
}