export default function CheckBox({hide, setHide}){
    return (
        <label>
            <input
        type="checkbox"
        checked={hide}
        onChange={()=> setHide(!hide)}
        />
        Hide Answers
        </label>
    );
}