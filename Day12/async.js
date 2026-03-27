setTimeout(() => {
    console.log("STEP-1");
    setTimeout(() => {
        console.log("STEP-2");
        setTimeout(() => {
            console.log("STEP-3");
        }, 1000);
    }, 1000);
}, 1000);